package com.eshc.domain.usecase.notification

import androidx.paging.PagingData
import com.eshc.domain.model.Notification
import com.eshc.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke() : Flow<PagingData<Notification>> {
        return notificationRepository.getNotifications()
    }
}