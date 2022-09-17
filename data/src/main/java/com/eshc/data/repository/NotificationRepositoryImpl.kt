package com.eshc.data.repository

import com.eshc.domain.model.Notification
import com.eshc.domain.repository.NotificationRepository
import io.reactivex.rxjava3.core.Single

class NotificationRepositoryImpl : NotificationRepository {
    override fun getNotifications(): Single<Result<List<Notification>>> {
        TODO("Not yet implemented")
    }

    override fun updateNotificationAsRead() {
        TODO("Not yet implemented")
    }
}