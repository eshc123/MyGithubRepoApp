package com.eshc.data.source

import io.reactivex.rxjava3.core.Single

interface AuthDataSource {

    fun getAccessToken(code : String) : Single<Result<String>>
}