package com.eshc.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.eshc.data.source.NotificationDataSource
import com.eshc.data.source.remote.api.GithubService
import com.eshc.data.source.remote.paging.NotificationPagingSource
import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationDataSourceImpl @Inject constructor(
    private val githubService: GithubService
) : NotificationDataSource{
    override fun getNotifications(): Flow<PagingData<Notification>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                NotificationPagingSource(githubService = githubService)
            }
        ).flow
    }

    override suspend fun updateNotificationAsRead(id : String)  {
        githubService.patchNotificationThread(id)
    }
}