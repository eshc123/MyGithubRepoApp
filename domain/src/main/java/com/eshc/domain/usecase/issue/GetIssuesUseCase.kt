package com.eshc.domain.usecase.issue

import androidx.paging.PagingData
import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import com.eshc.domain.repository.IssueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val issueRepository: IssueRepository
){
    operator fun invoke(state : IssueState) : Flow<PagingData<Issue>> {
        return issueRepository.getIssues(state)
    }
}