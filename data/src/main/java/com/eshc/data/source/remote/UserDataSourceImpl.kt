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
    override fun getUser(): Single<Result<User>> {
        return githubService.getUserData()
            .map {
                Result.success(it.body()?.toUser() ?: User())
            }
            .onErrorReturn {
                Result.failure(it.cause ?: Throwable("User Error"))
            }
    }

    override fun getUserStarred(): Single<Result<Int>> {
        return githubService.getStarredRepos()
            .map {
                Result.success(it.body()?.size ?: 0)
            }
            .onErrorReturn {
                Result.failure(it.cause ?: Throwable("Starred Error"))
            }
    }
}