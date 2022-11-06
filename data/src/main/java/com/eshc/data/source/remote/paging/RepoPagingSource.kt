package com.eshc.data.source.remote.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.eshc.data.model.RepoEntity
import com.eshc.data.model.toNotification
import com.eshc.data.model.toRepo
import com.eshc.data.source.remote.api.GithubService
import com.eshc.domain.model.Notification
import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RepoPagingSource @Inject constructor(
    private val githubService: GithubService,
    private val query : String
) : RxPagingSource<Int, Repo>() {

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Repo>> {
        val position = params.key ?: 1
        return githubService.getRepositories(page = position, searchText = query)
            .subscribeOn(Schedulers.io())
            .map {
                val repos = it.body()?.items ?: emptyList()
                val loadResult : LoadResult<Int, Repo> =
                    LoadResult.Page(
                        data = repos.map { repoEntity ->
                            repoEntity.toRepo()
                        },
                        prevKey = if(position == 1) null else position - 1,
                        nextKey = if(repos.isEmpty()) null else position + 1
                    )
                loadResult
            }
            .onErrorReturn {
                println(it.toString())
                LoadResult.Error(it)
            }
    }
}