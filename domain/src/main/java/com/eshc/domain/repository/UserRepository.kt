package com.eshc.domain.repository

import com.eshc.domain.model.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    suspend fun getUser() : Result<User>

    suspend fun getStarred() : Result<Int>
}