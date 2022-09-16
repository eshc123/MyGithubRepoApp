package com.eshc.domain.repository

import com.eshc.domain.model.Issue
import io.reactivex.rxjava3.core.Single

interface IssueRepository {
    fun getIssues(state : String) : Single<Result<List<Issue>>>
}