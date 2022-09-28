package com.eshc.data.repository

import com.eshc.data.source.UserDataSource
import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository{
    override fun getUser(): Single<Result<User>> {
        return try {
            userDataSource.getUser()
                .map {
                    Result.success(it.getOrThrow())
                }
                .onErrorReturn {
                    Result.failure(it.cause ?: Throwable())
                }
        } catch (e : Exception) {
            Single.create {
                Result.failure<String>(e)
            }
        }
    }
}
