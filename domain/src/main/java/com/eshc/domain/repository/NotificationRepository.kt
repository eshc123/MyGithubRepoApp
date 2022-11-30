package com.eshc.domain.repository

import androidx.paging.PagingData
import com.eshc.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getNotifications() : Flow<PagingData<Notification>>

    suspend fun updateNotificationAsRead(id : String)
}