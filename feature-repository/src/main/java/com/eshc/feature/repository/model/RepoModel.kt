package com.eshc.feature.repository.model

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