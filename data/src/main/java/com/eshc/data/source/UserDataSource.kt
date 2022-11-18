package com.eshc.data.source

import com.eshc.domain.model.User
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    suspend fun getUser() : Result<User>

    fun getUserStarred() : Single<Result<Int>>
}