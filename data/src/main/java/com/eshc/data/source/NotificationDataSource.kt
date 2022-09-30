package com.eshc.data.source

import androidx.paging.PagingData
import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface NotificationDataSource {
    fun getNotifications(): Flowable<PagingData<Notification>>

    fun updateNotificationAsRead(id : String)
}