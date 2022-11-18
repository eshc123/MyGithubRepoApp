package com.eshc.domain.repository

import io.reactivex.rxjava3.core.Single

interface AuthRepository {
    suspend fun getAccessToken(code : String) : Result<String>
}