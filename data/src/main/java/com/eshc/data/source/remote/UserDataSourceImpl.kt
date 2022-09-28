package com.eshc.data.source.remote

import com.eshc.data.model.toUser
import com.eshc.data.source.UserDataSource
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.User
import com.eshc.domain.model.defaultUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val githubService: GithubService
) : UserDataSource {
    override fun getUser(): Single<Result<User>> {
        return githubService.getUserData()
            .map {
                Result.success(it.body()?.toUser() ?: defaultUser())
            }
            .onErrorReturn {
                Result.failure(it.cause ?: Throwable("User Error"))
            }
    }
}