package com.eshc.data.source

import io.reactivex.rxjava3.core.Single

interface AuthDataSource {

    suspend fun getAccessToken(code : String) : Result<String>
}