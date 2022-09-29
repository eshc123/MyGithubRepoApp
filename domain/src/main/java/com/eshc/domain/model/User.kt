package com.eshc.domain.model

data class User(
    val id : Long = -1,
    val login : String = "",
    val avatarUrl : String = "",
    val name : String = "",
    val blog : String = "",
    val location : String = "",
    val email : String = "",
    val bio : String = "",
    val publicRepos : Int = 0,
    val followers : Int = 0,
    val following : Int = 0,
    val starred : Int = 0
)

