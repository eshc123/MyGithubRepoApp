package com.eshc.feature.notification.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.eshc.domain.usecase.notification.GetNotificationsUseCase
import com.eshc.domain.usecase.notification.UpdateNotificationsAsReadUseCase
import com.eshc.feature.notification.model.NotificationModel
import com.eshc.feature.notification.model.toNotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val updateNotificationsAsReadUseCase: UpdateNotificationsAsReadUseCase
) : ViewModel() {

    private val _notificationsToBeRemoved = MutableStateFlow<List<NotificationModel>>(
        mutableListOf()
    )
    val notificationsToBeRemoved: StateFlow<List<NotificationModel>> get() = _notificationsToBeRemoved
    val isEmptyNotificationsToBeRemoved: Boolean
        get() = notificationsToBeRemoved.value.isEmpty()

    val notifications =
        notificationsToBeRemoved.flatMapLatest { removeList ->
            getNotificationsUseCase()
                .map {
                    it.map {
                        it.toNotificationModel()
                    }
                    .filter {
                        removeList.map {
                            it.id
                        }.contains(it.id).not()
                    }
                }.cachedIn(viewModelScope)
        }

    val hasRemoved = MutableStateFlow<Boolean>(false)

    fun removeNotification(notification: NotificationModel) {
        _notificationsToBeRemoved.value = notificationsToBeRemoved.value + notification
    }

    fun removeAllNotifications() {
        if(isEmptyNotificationsToBeRemoved.not()) {
            viewModelScope.launch {
                updateNotificationsAsReadUseCase(
                    notificationsToBeRemoved.value.map {
                        it.id
                    }
                )
                _notificationsToBeRemoved.value = listOf()
                hasRemoved.value = true
            }
        }
    }

}