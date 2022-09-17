package com.eshc.data.source.remote

import com.eshc.data.source.NotificationDataSource
import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.core.Single

class NotificationDataSourceImpl : NotificationDataSource{
    override fun getNotifications(): Single<Result<List<Notification>>> {
        TODO("Not yet implemented")
    }

    override fun updateNotificationAsRead() {
        TODO("Not yet implemented")
    }
}