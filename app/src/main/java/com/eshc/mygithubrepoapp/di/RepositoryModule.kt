package com.eshc.mygithubrepoapp.di

import com.eshc.data.repository.*
import com.eshc.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepository : AuthRepositoryImpl
    ) : AuthRepository

    @Binds
    abstract fun bindIssueRepository(
        issueRepository: IssueRepositoryImpl
    ) : IssueRepository

    @Binds
    abstract fun bindNotificationRepository(
        notificationRepository: NotificationRepositoryImpl
    ) : NotificationRepository

    @Binds
    abstract fun bindRepoRepository(
        repoRepository: RepoRepositoryImpl
    ) : RepoRepository

    @Singleton
    @Binds
    abstract fun bindUserRepository(
        userRepository: UserRepositoryImpl
    ) : UserRepository
}