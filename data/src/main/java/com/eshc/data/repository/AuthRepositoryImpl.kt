package com.eshc.data.repository

import com.eshc.data.source.AuthDataSource
import com.eshc.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override fun getAccessToken(code : String): Single<Result<String>> {
        return try {
            authDataSource.getAccessToken(code)
                .map {
                    Result.success(it.getOrThrow())
                }
                .onErrorReturn {
                    Result.failure(it)
                }
        } catch (e : Exception) {
            Single.just(
                Result.failure(e)
            )
        }
    }
}