package com.eshc.domain.usecase.auth

import com.eshc.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(code : String) : Result<String> {
        return try {
            Result.success(authRepository.getAccessToken(code).getOrThrow())

        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}