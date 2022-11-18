package com.eshc.data.source.remote

import com.eshc.data.model.toUser
import com.eshc.data.source.UserDataSource
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val githubService: GithubService
) : UserDataSource {
    override suspend fun getUser(): Result<User> {
        try {
            val response = githubService.getUserData()
            if(response.isSuccessful){
                return Result.success(response.body()?.toUser() ?: User())
            } else {
                return Result.failure(Throwable("Can't get a user data"))
            }
        } catch (e : Exception){
            return Result.failure(e)
        }
    }

    override suspend fun getUserStarred(): Result<Int> {
        try {
            val response = githubService.getStarredRepos()
            if(response.isSuccessful){
                return Result.success(response.body()?.size ?: 0)
            } else {
                return Result.failure(Throwable("Can't get starred repos"))
            }
        } catch (e: Exception){
            return Result.failure(e)
        }
    }
}