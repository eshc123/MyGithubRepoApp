package com.eshc.feature.repository.model

import com.eshc.domain.model.Repo
import com.eshc.domain.model.User

data class RepoModel(
    val id : Int,
    val name : String,
    val description : String,
    val owner : User,
    val stargazersCount : Int,
    val language : String,
    val fullName : String,
    val starCnt : String,
    val languageColor : Int
)

fun Repo.toRepoModel() : RepoModel {
    return RepoModel(
        id = id,
        name = name,
        description = description,
        owner = owner,
        stargazersCount = stargazersCount,
        language = language,
        fullName = fullName,
        starCnt = "",
        languageColor = 0
    )
}