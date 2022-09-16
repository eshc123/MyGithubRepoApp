package com.eshc.domain.repository

import com.eshc.domain.model.User
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun getUser() : Single<Result<User>>
}