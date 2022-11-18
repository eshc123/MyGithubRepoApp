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

    override suspend fun getUser(): Result<User> {
        return try {
            if (user == null){
                userDataSource.getUser().getOrThrow().run {
                    user = this
                    Result.success(this)
                }
            } else {
                Result.success(user ?: User())
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun getStarred(): Single<Result<Int>> {
        try {
            return if(user?.starred == null) {
                userDataSource.getUserStarred()
                    .map {
                        it.getOrThrow().run {
                            user = user?.copy(
                                starred = this
                            )
                            Result.success(this)
                        }
                    }
                    .onErrorReturn {
                        Result.failure(it)
                    }
            } else Single.just(
                Result.success(user?.starred ?: 0)
            )
        } catch (e: Exception) {
            return Single.create {
                Result.failure<String>(e)
            }
        }
    }
}
