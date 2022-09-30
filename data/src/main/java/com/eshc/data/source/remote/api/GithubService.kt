package com.eshc.data.source.remote.api

import com.eshc.data.model.NotificationEntity
import com.eshc.data.model.StarredEntity
import com.eshc.data.model.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/user")
    fun getUserData(): Single<Response<UserEntity>>

    @GET("/notifications")
    fun getNotifications(
        @Query("per_page") perPage: Int = 10, // default = 30
        @Query("page") page: Int
    ): Single<Response<List<NotificationEntity>>>

    @GET("/user/starred")
    fun getStarredRepos(): Single<Response<List<StarredEntity>>>

    @PATCH("/notifications/threads/{thread_id}")
    fun patchNotificationThread(
        @Path("thread_id") threadId: String
    ): Single<Response<Unit>>
}