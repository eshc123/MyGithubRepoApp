package com.eshc.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.eshc.data.source.remote.NotificationPagingSource
import com.eshc.domain.model.Notification
import com.eshc.domain.repository.NotificationRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationPagingSource: NotificationPagingSource
) : NotificationRepository {
    override fun getNotifications(): Flowable<PagingData<Notification>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                notificationPagingSource
            }
        ).flowable
    }

    override fun updateNotificationAsRead() {
        TODO("Not yet implemented")
    }
}