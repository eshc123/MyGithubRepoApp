package com.eshc.data.repository

import com.eshc.domain.model.Repo
import com.eshc.domain.repository.RepoRepository
import io.reactivex.rxjava3.core.Single

class RepoRepositoryImpl : RepoRepository {
    override fun getRepos(): Single<Result<List<Repo>>> {
        TODO("Not yet implemented")
    }
}