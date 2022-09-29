package com.eshc.data.repository

import com.eshc.data.source.UserDataSource
import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    private var user: User? = null

    override fun getUser(): Single<Result<User>> {
        try {
            return if (user == null)
                userDataSource.getUser()
                    .map {
                        it.getOrThrow().run {
                            user = this
                            Result.success(this)
                        }
                    }
                    .onErrorReturn {
                        Result.failure(it.cause ?: Throwable())
                    }
            else Single.just (
                Result.success(user ?: User())
            )
        } catch (e: Exception) {
            return Single.create {
                Result.failure<String>(e)
            }
        }
    }

    override fun getStarred(): Single<Result<Int>> {
        try {
            return userDataSource.getUserStarred()
                .map {
                    it.getOrThrow().run {
                        Result.success(this)
                    }
                }
                .onErrorReturn {
                    Result.failure(it.cause ?: Throwable())
                }
        } catch (e: Exception) {
            return Single.create {
                Result.failure<String>(e)
            }
        }
    }
}
