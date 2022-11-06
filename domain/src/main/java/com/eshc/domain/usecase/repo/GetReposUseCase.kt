package com.eshc.domain.usecase.repo

import androidx.paging.PagingData
import com.eshc.domain.model.Repo
import com.eshc.domain.repository.RepoRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(query : String) : Flowable<PagingData<Repo>> {
        return repoRepository.getRepos(query)
    }
}