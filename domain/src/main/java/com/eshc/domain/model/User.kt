package com.eshc.domain.model

data class User(
    val id : Long,
    val login : String,
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

fun defaultUser() : User {
    return User(
        id = -1,
        login = "",
        avatarUrl = "",
        name = "",
        blog = "",
        location = "",
        email = "",
        bio = "",
        publicRepos = 0,
        followers = 0,
        following = 0
    )
}
