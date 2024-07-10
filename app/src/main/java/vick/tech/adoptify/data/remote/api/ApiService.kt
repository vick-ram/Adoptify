package vick.tech.adoptify.data.remote.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import vick.tech.adoptify.core.auth.TokenResponse
import vick.tech.adoptify.data.remote.api.Endpoints.ANIMALS
import vick.tech.adoptify.data.remote.api.Endpoints.TOKEN
import vick.tech.adoptify.data.remote.dto.animals.AnimalsResponse

/**
 * The endpoint interface that communicates with the external api
 */
interface ApiService {

    /**
     * This function getAccessToken requests the access token
     * and refreshes it each time it expires
     */
    @FormUrlEncoded
    @POST(TOKEN)
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ) : Response<TokenResponse>

    /**
     * The getAllAnimals function requests for animals in an endpoint
     * by passing in query parameters
     */
    @GET(ANIMALS)
    suspend fun getAllAnimals(
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): AnimalsResponse
}
