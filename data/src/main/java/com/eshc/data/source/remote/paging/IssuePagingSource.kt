package com.eshc.data.source.remote.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.eshc.data.model.toIssue
import com.eshc.data.model.toNotification
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class IssuePagingSource @Inject constructor(
    private val githubService: GithubService,
    private val state: IssueState
) : RxPagingSource<Int, Issue>() {
    override fun getRefreshKey(state: PagingState<Int, Issue>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Issue>> {
        val position = params.key ?: 1
        return githubService.getIssues(
            page = position,
            state = state.name
            )
            .subscribeOn(Schedulers.io())
            .map {
                val issues = it.body() ?: emptyList()
                val loadResult : LoadResult<Int, Issue> =
                    LoadResult.Page(
                        data = issues.map { issueEntity ->
                            issueEntity.toIssue()
                        },
                        prevKey = if(position == 1) null else position - 1,
                        nextKey = if(issues.isEmpty()) null else position + 1
                    )
                loadResult
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

}