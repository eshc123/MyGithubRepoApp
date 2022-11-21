package com.eshc.domain.repository

import androidx.paging.PagingData
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    fun getIssues(state : IssueState) : Flow<PagingData<Issue>>
}