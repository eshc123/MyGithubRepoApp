package com.eshc.mygithubrepoapp

import com.eshc.data.source.remote.api.AuthService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceTest {

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val client =  OkHttpClient.Builder()
        .connectTimeout(3,TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://github.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Test
    fun `AccessToken을 받아올 수 있다`() {
    val service = retrofit.create(AuthService::class.java)

    service
        .getAccessToken(
            code = "" // Github Login으로 받아온 Code 입력
        )
        .test()
        .awaitDone(3, TimeUnit.SECONDS)
        .assertValue {
            println(it.body()?.accessToken)
            it.toString().isNotEmpty()
        }
        .assertComplete()
    }
}