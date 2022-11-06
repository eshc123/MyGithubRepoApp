package com.eshc.data.source

import androidx.paging.PagingData
import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Flowable

interface RepoDataSource {
    fun getRepos(query : String) : Flowable<PagingData<Repo>>
}