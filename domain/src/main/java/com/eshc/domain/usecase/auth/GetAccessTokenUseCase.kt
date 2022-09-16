package com.eshc.domain.usecase.auth

import com.eshc.domain.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(code : String) : Single<Result<String>> {
       return try {
           authRepository.getAccessToken(code)
               .map {
                   Result.success(it.getOrThrow())
               }
               .onErrorReturn {
                   Result.failure(it.cause ?: Throwable())
               }
       } catch (e: Exception){
           Single.create {
               Result.failure<String>(e)
           }
       }
    }
}