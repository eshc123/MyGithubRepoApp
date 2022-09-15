package com.eshc.mygithubrepoapp.di

import com.eshc.data.preference.AuthPreferences
import com.eshc.data.source.remote.api.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GithubRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @InterceptorOkHttpClient okHttpClient: OkHttpClient,
        converter : Converter.Factory,
        callAdapter: CallAdapter.Factory
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://github.com")
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    @GithubRetrofit
    @Provides
    @Singleton
    fun provideGithubRetrofit(
        @TokenInterceptorOkHttpClient okHttpClient: OkHttpClient,
        converter : Converter.Factory,
        callAdapter: CallAdapter.Factory
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com")
            .addConverterFactory(converter)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    @Provides
    @Singleton
    fun provideConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory() : CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @InterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @TokenInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideAuthHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(
        authPreferences: AuthPreferences
    ) : TokenInterceptor {
        return TokenInterceptor(authPreferences)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }
}