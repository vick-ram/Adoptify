package vick.tech.adoptify.core.auth

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class TokenResponse(
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("access_token") val accessToken: String
)

data class ApiError(
    val type: String,
    val status: Int,
    val title: String,
    val detail: String,
    val invalidParams: List<InvalidParam>?
)

data class InvalidParam(
    val `in`: String,
    val path: String,
    val message: String
)


fun <T> handleApiErrors(response: Response<T>): ApiError? {
    val errorBody = response.errorBody()?.string() ?: return null
    return Gson().fromJson(errorBody, ApiError::class.java)
}


