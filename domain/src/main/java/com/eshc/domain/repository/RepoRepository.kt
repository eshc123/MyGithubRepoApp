package com.eshc.domain.repository

import com.eshc.domain.model.Repo
import io.reactivex.rxjava3.core.Single

interface RepoRepository {
    fun getRepos() : Single<Result<List<Repo>>>
}