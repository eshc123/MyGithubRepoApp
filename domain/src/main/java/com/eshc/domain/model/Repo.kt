package com.eshc.domain.model

data class Repo(
    val id : Int,
    val name : String,
    val description : String,
    val owner : User,
    val stargazersCount : Int,
    val language : String,
    val fullName : String
)
