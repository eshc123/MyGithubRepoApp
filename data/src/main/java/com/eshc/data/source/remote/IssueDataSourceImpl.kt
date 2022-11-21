package com.eshc.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eshc.data.source.IssueDataSource
import com.eshc.data.source.remote.api.GithubService
import com.eshc.data.source.remote.paging.IssuePagingSource
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IssueDataSourceImpl  @Inject constructor(
    private val githubService: GithubService,
): IssueDataSource {
    override fun getIssues(state: IssueState): Flow<PagingData<Issue>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                IssuePagingSource(
                    githubService = githubService,
                    state = state
                )
            }
        ).flow
    }

}