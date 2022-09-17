package com.eshc.data.repository

import com.eshc.domain.model.Issue
import com.eshc.domain.repository.IssueRepository
import io.reactivex.rxjava3.core.Single

class IssueRepositoryImpl : IssueRepository {
    override fun getIssues(state: String): Single<Result<List<Issue>>> {
        TODO("Not yet implemented")
    }
}