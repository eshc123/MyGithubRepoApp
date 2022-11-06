package com.eshc.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.eshc.data.source.RepoDataSource
import com.eshc.data.source.remote.api.GithubService
import com.eshc.data.source.remote.paging.NotificationPagingSource
import com.eshc.data.source.remote.paging.RepoPagingSource
import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepoDataSourceImpl @Inject constructor(
    private val githubService: GithubService
) : RepoDataSource {
    override fun getRepos(query : String): Flowable<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                RepoPagingSource(githubService = githubService,query = query)
            }
        ).flowable
    }
}