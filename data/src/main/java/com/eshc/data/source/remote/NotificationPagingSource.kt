package com.eshc.data.source.remote

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.eshc.data.model.NotificationEntity
import com.eshc.data.model.toNotification
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.Notification
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NotificationPagingSource @Inject constructor(
    private val githubService: GithubService
) : RxPagingSource<Int, Notification>() {

    override fun getRefreshKey(state: PagingState<Int, Notification>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Notification>> {
        val position = params.key ?: 1
        return githubService.getNotifications(page = position)
            .subscribeOn(Schedulers.io())
            .map {
                val notifications = it.body() ?: emptyList()
                val loadResult : LoadResult<Int,Notification> =
                    LoadResult.Page(
                        data = notifications.map { notificationEntity ->
                            notificationEntity.toNotification()
                        },
                        prevKey = if(position == 1) null else position - 1,
                        nextKey = if(notifications.isEmpty()) null else position + 1
                    )
                loadResult
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }


}