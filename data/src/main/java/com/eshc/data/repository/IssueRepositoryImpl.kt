package com.eshc.data.repository

import androidx.paging.PagingData
import com.eshc.data.source.IssueDataSource
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import com.eshc.domain.repository.IssueRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class IssueRepositoryImpl @Inject constructor(
    private val issueDataSource: IssueDataSource
) : IssueRepository {
    override fun getIssues(state: IssueState): Flowable<PagingData<Issue>> {
        return issueDataSource.getIssues(state)
    }
}