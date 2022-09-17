package com.eshc.data.source.remote

import com.eshc.data.source.RepoDataSource
import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Single

class RepoDataSourceImpl : RepoDataSource {
    override fun getRepos(): Single<Result<List<Repo>>> {
        TODO("Not yet implemented")
    }
}