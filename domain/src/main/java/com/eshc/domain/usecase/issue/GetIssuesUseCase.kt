package com.eshc.domain.usecase.issue

import androidx.paging.PagingData
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import com.eshc.domain.repository.IssueRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val issueRepository: IssueRepository
){
    operator fun invoke(state : IssueState) : Flowable<PagingData<Issue>> {
        return issueRepository.getIssues(state)
    }
}