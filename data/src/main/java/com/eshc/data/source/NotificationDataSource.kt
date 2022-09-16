package com.eshc.data.source

import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.core.Single

interface NotificationDataSource {
    fun getNotifications() : Single<Result<List<Notification>>>

    fun updateNotificationAsRead()
}