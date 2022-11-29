package com.eshc.data.repository

import androidx.paging.PagingData
import com.eshc.data.source.NotificationDataSource
import com.eshc.domain.model.Notification
import com.eshc.domain.repository.NotificationRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDataSource : NotificationDataSource
) : NotificationRepository {
    override fun getNotifications(): Flow<PagingData<Notification>> {
        return notificationDataSource.getNotifications()
    }

    override fun updateNotificationAsRead(id : String) {
        notificationDataSource.updateNotificationAsRead(id)
    }
}