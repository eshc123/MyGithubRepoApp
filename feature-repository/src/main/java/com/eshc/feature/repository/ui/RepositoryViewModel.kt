package com.eshc.feature.repository.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.eshc.domain.usecase.repo.GetReposUseCase
import com.eshc.feature.repository.model.RepoModel
import com.eshc.feature.repository.model.toRepoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _repos = MutableLiveData<PagingData<RepoModel>>()
    val repos: LiveData<PagingData<RepoModel>> get() = _repos

    private val _searchWord = MutableLiveData<String>("")
    val searchWord : LiveData<String> = _searchWord

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getRepos(query : String){
        if(query.isNotEmpty()) {
            addDisposable(
                getReposUseCase(query = query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {
                        it.map { repo ->
                            repo.toRepoModel()
                        }
                    }.cachedIn(
                        viewModelScope
                    )
                    .subscribe {
                        _repos.value = it
                    }
            )
        }
    }

    fun setSearchWord(query: String){
        _searchWord.value = query
        addDisposable(
            Observable
                .create<String> { emitter ->
                    emitter.onNext(query)
                }
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    getRepos(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}