package com.eshc.domain.usecase.user

import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() : Result<User> {
        return try {
            Result.success(userRepository.getUser().getOrThrow())
        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}