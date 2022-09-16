package com.eshc.domain.repository

import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.core.Single

interface NotificationRepository {
    fun getNotifications() : Single<Result<List<Notification>>>

    fun updateNotificationAsRead()
}