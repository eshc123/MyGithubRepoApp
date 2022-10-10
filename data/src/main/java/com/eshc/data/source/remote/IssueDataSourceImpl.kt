package com.eshc.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.eshc.data.source.IssueDataSource
import com.eshc.data.source.remote.api.GithubService
import com.eshc.data.source.remote.paging.IssuePagingSource
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class IssueDataSourceImpl  @Inject constructor(
    private val githubService: GithubService,
): IssueDataSource {
    override fun getIssues(state: IssueState): Flowable<PagingData<Issue>> {
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
        ).flowable
    }

}