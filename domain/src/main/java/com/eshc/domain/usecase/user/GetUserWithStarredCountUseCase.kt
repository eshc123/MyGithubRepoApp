package com.eshc.domain.usecase.user

import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetUserWithStarredCountUseCase  @Inject constructor(
    private val userRepository: UserRepository,
    private val getUserProfileUseCase: GetUserProfileUseCase
) {
    operator fun invoke() : Single<Result<User>> {
        return try {
            userRepository.getStarred()
                .zipWith(getUserProfileUseCase()){ starred, user ->
                    Result.success(
                        user.getOrThrow().copy(
                            starred = starred.getOrThrow()
                        )
                    )
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