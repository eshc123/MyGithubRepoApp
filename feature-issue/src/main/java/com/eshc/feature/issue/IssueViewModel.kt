package com.eshc.feature.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.eshc.feature.issue.model.IssueModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(

) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _issues = MutableLiveData<PagingData<IssueModel>>()
    val issues: LiveData<PagingData<IssueModel>> get() = _issues

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}