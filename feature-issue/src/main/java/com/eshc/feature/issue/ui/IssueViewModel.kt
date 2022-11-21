package com.eshc.feature.issue.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.eshc.domain.model.IssueState
import com.eshc.domain.usecase.issue.GetIssuesUseCase
import com.eshc.feature.issue.model.IssueModel
import com.eshc.feature.issue.model.toIssueModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*

import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase
) : ViewModel() {

    private val _issueState = MutableStateFlow<IssueState>(IssueState.open)
    val issueState : StateFlow<IssueState> get() = _issueState

    val issues = issueState.flatMapLatest {
        getIssuesUseCase(state = it).map { it.map { it.toIssueModel() }  }.cachedIn(viewModelScope)
    }

    fun setIssueState(state : IssueState){
        _issueState.value = state
    }
}