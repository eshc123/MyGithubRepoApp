package com.eshc.mygithubrepoapp.di

import android.content.Context
import com.eshc.data.preference.AuthPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun providePreferences(
        @ApplicationContext context : Context
    ) : AuthPreferences {
        return AuthPreferences(context)
    }

}