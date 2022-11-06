package com.eshc.domain.repository

import androidx.paging.PagingData
import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface RepoRepository {
    fun getRepos(query: String) : Flowable<PagingData<Repo>>
}