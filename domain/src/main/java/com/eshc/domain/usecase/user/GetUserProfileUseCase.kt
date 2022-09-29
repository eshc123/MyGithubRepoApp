package com.eshc.domain.usecase.user

import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() : Single<Result<User>> {
        return try {
            userRepository.getUser()
                .map {
                    Result.success(it.getOrThrow())
                }
                .onErrorReturn {
                    Result.failure(it)
                }
        } catch (e : Exception) {
            Single.create {
                Result.failure<String>(e)
            }
        }
    }
}