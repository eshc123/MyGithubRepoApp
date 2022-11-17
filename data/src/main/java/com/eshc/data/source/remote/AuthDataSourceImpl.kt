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
    override suspend fun getAccessToken(code : String): Result<String> {

        val response = authService.getAccessToken(code = code)
        return try {
            if (response.isSuccessful){
                Result.success(response.body()?.accessToken ?: "")
            } else {
                Result.failure(Throwable("Can't Get Access Token"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}