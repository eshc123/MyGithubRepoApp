package com.eshc.feature.notification.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.eshc.domain.usecase.notification.GetNotificationsUseCase
import com.eshc.domain.usecase.notification.UpdateNotificationsAsReadUseCase
import com.eshc.feature.notification.model.NotificationModel
import com.eshc.feature.notification.model.toNotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val updateNotificationsAsReadUseCase: UpdateNotificationsAsReadUseCase
) : ViewModel() {

    val notifications = getNotificationsUseCase().map { it.map { it.toNotificationModel() } }.cachedIn(viewModelScope)

    private val notificationsToBeRemoved : MutableList<NotificationModel> = mutableListOf()
    val isEmptyNotificationsToBeRemoved : Boolean
        get() = notificationsToBeRemoved.isEmpty()



//    fun removeNotification(notification: NotificationModel) {
//        _notifications.value = _notifications.value?.filter {
//            it.id != notification.id
//        }
//        notificationsToBeRemoved.add(notification)
//    }
//
//    fun removeAllNotifications() {
//        if(isEmptyNotificationsToBeRemoved.not()) {
//            updateNotificationsAsReadUseCase(
//                notificationsToBeRemoved.map {
//                    it.id
//                }
//            )
//            notificationsToBeRemoved.clear()
//            getNotifications()
//        }
//    }

}