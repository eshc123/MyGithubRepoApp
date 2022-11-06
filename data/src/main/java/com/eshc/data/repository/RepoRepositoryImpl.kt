package com.eshc.data.repository

import androidx.paging.PagingData
import com.eshc.data.source.RepoDataSource
import com.eshc.domain.model.Repo
import com.eshc.domain.repository.RepoRepository
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDataSource: RepoDataSource
) : RepoRepository {
    override fun getRepos(query: String): Flowable<PagingData<Repo>> {
        return repoDataSource.getRepos(query)
    }
}