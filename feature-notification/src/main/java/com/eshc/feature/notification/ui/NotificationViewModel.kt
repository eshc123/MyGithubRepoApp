package com.eshc.feature.notification.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.eshc.domain.usecase.notification.GetNotificationsUseCase
import com.eshc.feature.notification.model.NotificationModel
import com.eshc.feature.notification.model.toNotificationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _notifications = MutableLiveData<PagingData<NotificationModel>>()
    val notifications: LiveData<PagingData<NotificationModel>> get() = _notifications

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNotifications() {
        addDisposable(
            getNotificationsUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.map { notification ->
                        notification.toNotificationModel()
                    }
                }.cachedIn(viewModelScope)
                .subscribe {
                    _notifications.value = it
                }
        )
    }

    fun removeNotification(id : String) {
        _notifications.value = _notifications.value?.filter {
            it.id != id
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}