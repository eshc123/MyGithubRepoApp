package com.eshc.domain.usecase.notification

import androidx.paging.PagingData
import com.eshc.domain.model.Notification
import com.eshc.domain.repository.NotificationRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    val notificationRepository: NotificationRepository
) {
    operator fun invoke() : Flowable<PagingData<Notification>> {
        return notificationRepository.getNotifications()
    }
}