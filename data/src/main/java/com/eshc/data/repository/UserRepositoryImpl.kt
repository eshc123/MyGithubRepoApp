package com.eshc.data.repository

import com.eshc.domain.model.User
import com.eshc.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Single

class UserRepositoryImpl : UserRepository{
    override fun getUser(): Single<Result<User>> {
        TODO("Not yet implemented")
    }
}