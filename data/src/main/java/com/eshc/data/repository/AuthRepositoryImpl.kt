package com.eshc.data.repository

import com.eshc.data.source.AuthDataSource
import com.eshc.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun getAccessToken(code : String): Result<String> {
        return try {
            Result.success(authDataSource.getAccessToken(code).getOrThrow())

        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}