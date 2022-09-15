package com.eshc.data.source.remote.api

import com.eshc.data.preference.AuthPreferences
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val authPreferences: AuthPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder().apply {
            header("Authorization", "token ${authPreferences.accessToken}")
            method(original.method, original.body)
        }.build()

        return chain.proceed(request)
    }
}