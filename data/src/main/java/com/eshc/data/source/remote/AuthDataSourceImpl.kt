package com.eshc.data.source.remote

import com.eshc.data.preference.AuthPreferences
import com.eshc.data.source.AuthDataSource
import com.eshc.data.source.remote.api.AuthService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val authPreferences: AuthPreferences
) : AuthDataSource {
    override fun getAccessToken(code : String): Single<Result<String>> {

        return authService.getAccessToken(code = code)
            .map {
                val accessToken = it.body()?.accessToken
                if(accessToken != null) {
                    authPreferences.accessToken = accessToken
                    Result.success(it.body()?.accessToken ?: "")
                } else {
                    Result.failure(Throwable("Token Error"))
                }

            }
            .onErrorReturn {
                Result.failure(it.cause ?: Throwable("Token Error"))
            }
    }
}