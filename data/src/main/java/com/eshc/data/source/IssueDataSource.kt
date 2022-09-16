package com.eshc.data.source

import com.eshc.domain.model.Issue
import io.reactivex.rxjava3.core.Single

interface IssueDataSource {
    fun getIssues(state : String) : Single<Result<List<Issue>>>
}