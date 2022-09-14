package com.eshc.mygithubrepoapp.di

import com.eshc.data.source.*
import com.eshc.data.source.remote.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAuthDataSource(
        authDataSource : AuthDataSourceImpl
    ) : AuthDataSource

    @Binds
    abstract fun bindIssueDataSource(
        issueDataSource : IssueDataSourceImpl
    ) : IssueDataSource

    @Binds
    abstract fun bindNotificationDataSource(
        notificationDataSource : NotificationDataSourceImpl
    ) : NotificationDataSource

    @Binds
    abstract fun bindRepoDataSource(
        repoDataSource: RepoDataSourceImpl
    ) : RepoDataSource

    @Binds
    abstract fun bindUserDataSource(
        userDataSource: UserDataSourceImpl
    ) : UserDataSource

}