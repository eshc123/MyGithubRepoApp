package com.eshc.data.source.remote

import com.eshc.data.source.IssueDataSource
import com.eshc.domain.model.Issue
import io.reactivex.rxjava3.core.Single

class IssueDataSourceImpl : IssueDataSource {
    override fun getIssues(state: String): Single<Result<List<Issue>>> {
        TODO("Not yet implemented")
    }
}