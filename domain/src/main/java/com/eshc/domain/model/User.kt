package com.eshc.domain.model

data class User(
    val id : Int,
    val avatarUrl : String,
    val name : String,
    val blog : String,
    val location : String,
    val email : String,
    val bio : String,
    val publicRepos : Int,
    val followers : Int,
    val following : Int
)
