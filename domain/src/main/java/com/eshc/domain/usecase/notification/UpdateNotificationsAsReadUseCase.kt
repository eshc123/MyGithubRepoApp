package com.eshc.domain.usecase.notification

import com.eshc.domain.repository.NotificationRepository
import javax.inject.Inject

class UpdateNotificationsAsReadUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
)  {
    suspend operator fun invoke(ids: List<String>) {
        ids.forEach {
            notificationRepository.updateNotificationAsRead(it)
        }
    }
}