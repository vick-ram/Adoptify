package vick.tech.adoptify.core.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import vick.tech.adoptify.core.key.clientId
import vick.tech.adoptify.core.key.clientSecret
import vick.tech.adoptify.core.key.grantType
import vick.tech.adoptify.data.remote.api.ApiService
import javax.inject.Inject
import javax.inject.Provider

class AuthInterceptor @Inject constructor(
    private val apiService: Provider<ApiService>,
    private val dStore: vick.tech.adoptify.data.util.DataStore
) : Interceptor {

    private var lastToken: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val (token, expiry) = runBlocking { dStore.readAccessToken() }
        val isExpired = System.currentTimeMillis() > expiry
        val validToken =
            if (token.isNotEmpty() && !isExpired) token else runBlocking { refreshToken() } ?: ""

        lastToken = validToken

        // val token = lastToken ?: runBlocking { dStore.readAccessToken().first }.also { lastToken = it }

        var response = makeRequestWithToken(chain, originalRequest, validToken)

        if (response.code == 401 && isExpired) {
            synchronized(this) {
                val newToken = runBlocking { refreshToken() }
                if (newToken != null) {
                    lastToken = newToken
                    response = makeRequestWithToken(chain, originalRequest, newToken)
                }
                response = makeRequestWithToken(chain, originalRequest, token)
            }
        }
        return response
    }

    private fun makeRequestWithToken(
        chain: Interceptor.Chain,
        originalRequest: okhttp3.Request,
        token: String?
    ): Response {
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }

    suspend fun refreshToken(): String? {
        try {
            val response = apiService.get().getAccessToken(
                grantType = grantType,
                clientSecret = clientSecret,
                clientId = clientId
            )
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    dStore.saveAccessToken(
                        token = result.accessToken,
                        expiry = System.currentTimeMillis() + (result.expiresIn * 1000)
                    )
                }
                return response.body()?.accessToken
            }
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}