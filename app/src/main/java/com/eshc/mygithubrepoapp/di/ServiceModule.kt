package com.eshc.mygithubrepoapp.di

import com.eshc.data.source.remote.api.AuthService
import com.eshc.data.source.remote.api.GithubService
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
        @AuthRetrofit retrofit: Retrofit
    ) : AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubService(
        @GithubRetrofit retrofit: Retrofit
    ) : GithubService {
        return retrofit.create(GithubService::class.java)
    }
}