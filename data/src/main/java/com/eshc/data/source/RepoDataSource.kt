package com.eshc.data.source

import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Single

interface RepoDataSource {
    fun getRepos() : Single<Result<List<Repo>>>
}