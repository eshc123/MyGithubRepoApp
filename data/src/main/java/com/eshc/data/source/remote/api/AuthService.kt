package com.eshc.data.source.remote.api

import com.eshc.data.BuildConfig
import com.eshc.data.model.AccessTokenEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @FormUrlEncoded
    @POST("/login/oauth/access_token")
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String = BuildConfig.githubClientId,
        @Field("client_secret") clientSecret: String = BuildConfig.githubClientSecret,
        @Field("code") code: String
    ) : Response<AccessTokenEntity>
}