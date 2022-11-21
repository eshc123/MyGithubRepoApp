package com.eshc.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eshc.data.model.toIssue
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import javax.inject.Inject

class IssuePagingSource @Inject constructor(
    private val githubService: GithubService,
    private val state: IssueState
) : PagingSource<Int, Issue>() {
    override fun getRefreshKey(state: PagingState<Int, Issue>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Issue> {
        val position = params.key ?: 1
        try {
            val issues = githubService.getIssues(
                page = position,
                state = state.name
            ).body() ?: emptyList()

            return LoadResult.Page(
                data = issues.map {
                    it.toIssue()
                },
                prevKey = if(position == 1) null else position - 1,
                nextKey = if(issues.isEmpty()) null else position + 1
            )
        } catch (e : Exception){
            return LoadResult.Error(e)
        }
    }

}