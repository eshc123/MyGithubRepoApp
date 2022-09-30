package com.eshc.domain.repository

import androidx.paging.PagingData
import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface NotificationRepository {
    fun getNotifications() : Flowable<PagingData<Notification>>

    fun updateNotificationAsRead(id : String)
}