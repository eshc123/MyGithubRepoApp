package com.eshc.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eshc.data.model.toNotification
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.Notification
import javax.inject.Inject

class NotificationPagingSource @Inject constructor(
    private val githubService: GithubService
) : PagingSource<Int, Notification>() {
    override fun getRefreshKey(state: PagingState<Int, Notification>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Notification> {
        val position = params.key ?: 1
        try {
            val notifications = githubService.getNotifications(
                page = position
            ).body() ?: emptyList()

            return PagingSource.LoadResult.Page(
                data = notifications.map {
                    it.toNotification()
                },
                prevKey = if(position == 1) null else position - 1,
                nextKey = if(notifications.isEmpty()) null else position + 1
            )
        } catch (e : Exception){
            return PagingSource.LoadResult.Error(e)
        }
    }
}