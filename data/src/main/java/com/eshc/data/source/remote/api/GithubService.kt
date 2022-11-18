package com.eshc.data.source.remote.api

import com.eshc.data.model.*
import com.eshc.data.model.response.RepoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/user")
    suspend fun getUserData(): Response<UserEntity>

    @GET("/notifications")
    fun getNotifications(
        @Query("per_page") perPage: Int = 10, // default = 30
        @Query("page") page: Int
    ): Single<Response<List<NotificationEntity>>>

    @GET("/user/starred")
    suspend fun getStarredRepos(): Response<List<StarredEntity>>

    @PATCH("/notifications/threads/{thread_id}")
    fun patchNotificationThread(
        @Path("thread_id") threadId: String
    ): Single<Response<Unit>>

    @GET("/issues")
    fun getIssues(
        @Query("filter") filter: String = "all",
        @Query("state") state: String,
        @Query("per_page") perPage: Int = 10, //default = 30
        @Query("page") page: Int,
        @Query("sort") sort: String = "updated" //default = "created"
    ): Single<Response<List<IssueEntity>>>

    @GET("/search/repositories")
    fun getRepositories(
        @Query("q") searchText: String,
        @Query("sort") sort: String = "start",
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int
    ): Single<Response<RepoResponse>>
}