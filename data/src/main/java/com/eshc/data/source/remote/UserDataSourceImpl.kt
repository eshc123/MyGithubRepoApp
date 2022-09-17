package com.eshc.data.source.remote

import com.eshc.data.source.UserDataSource
import com.eshc.domain.model.User
import io.reactivex.rxjava3.core.Single

class UserDataSourceImpl : UserDataSource {
    override fun getUser(): Single<Result<User>> {
        TODO("Not yet implemented")
    }
}