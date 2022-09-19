package com.eshc.data.source.remote.api

import com.eshc.data.model.NotificationEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/notifications")
    fun getNotifications(
        @Query("per_page") perPage: Int = 10, // default = 30
        @Query("page") page: Int
    ): Single<Response<List<NotificationEntity>>>

}