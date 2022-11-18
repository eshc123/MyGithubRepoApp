package com.eshc.domain.usecase.user

import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserWithStarredCountUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserProfileUseCase: GetUserProfileUseCase
) {
    suspend operator fun invoke(): Result<User> {
        return try {
            userRepository.getStarred().map {
                getUserProfileUseCase().getOrThrow().copy(
                    starred = it
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}