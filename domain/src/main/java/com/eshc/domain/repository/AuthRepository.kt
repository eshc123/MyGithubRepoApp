package com.eshc.domain.repository

import io.reactivex.rxjava3.core.Single

interface AuthRepository {
    fun getAccessToken(code : String) : Single<Result<String>>
}