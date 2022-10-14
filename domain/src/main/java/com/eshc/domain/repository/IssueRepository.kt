package com.eshc.domain.repository

import androidx.paging.PagingData
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IssueRepository {
    fun getIssues(state : IssueState) : Flowable<PagingData<Issue>>
}