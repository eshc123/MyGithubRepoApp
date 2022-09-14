package com.eshc.mygithubrepoapp.di

import com.eshc.data.source.remote.api.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(
        retrofit: Retrofit
    ) : AuthService {
        return retrofit.create(AuthService::class.java)
    }
}