package com.eshc.data.source

import androidx.paging.Pager
import androidx.paging.PagingData
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface IssueDataSource {
    fun getIssues(state : IssueState) : Flow<PagingData<Issue>>
}