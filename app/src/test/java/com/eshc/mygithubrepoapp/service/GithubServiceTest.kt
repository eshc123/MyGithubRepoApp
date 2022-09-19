package com.eshc.mygithubrepoapp.service

import com.eshc.data.preference.AuthPreferences
import com.eshc.data.source.remote.api.GithubService
import com.eshc.data.source.remote.api.TokenInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class GithubServiceTest {

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val authPreferences : AuthPreferences = AuthPreferences(context = RuntimeEnvironment.getApplication().applicationContext).apply {
        this.accessToken = "" //TODO GetAccessToken 자동화
    }

    private val interceptor : TokenInterceptor = TokenInterceptor(
        authPreferences = authPreferences
    )

    private val client =  OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Test
    fun `Notification_목록을_받아올_수_있다`() {
        val service = retrofit.create(GithubService::class.java)

        service
            .getNotifications(10,1)
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue {
                it.code() == 200
            }
            .assertComplete()
    }
}