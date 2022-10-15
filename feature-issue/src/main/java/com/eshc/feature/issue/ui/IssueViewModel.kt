package com.eshc.feature.issue.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.eshc.domain.model.IssueState
import com.eshc.domain.usecase.issue.GetIssuesUseCase
import com.eshc.feature.issue.model.IssueModel
import com.eshc.feature.issue.model.toIssueModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _issues = MutableLiveData<PagingData<IssueModel>>()
    val issues: LiveData<PagingData<IssueModel>> get() = _issues

    private val _issueState = MutableLiveData<IssueState>()
    val issueState : LiveData<IssueState> get() = _issueState


    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getIssues(){
        addDisposable(
            getIssuesUseCase(state = IssueState.all)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.map { issue ->
                        issue.toIssueModel()
                    }
                }.cachedIn(
                    viewModelScope
                )
                .subscribe {
                    _issues.value = it
                }
        )
    }

    fun setIssueState(state : IssueState){
        _issueState.value = state
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}