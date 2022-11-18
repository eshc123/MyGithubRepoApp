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

    override fun getUserStarred(): Single<Result<Int>> {
        return githubService.getStarredRepos()
            .map {
                Result.success(it.body()?.size ?: 0)
            }
            .onErrorReturn {
                Result.failure(it)
            }
    }
}