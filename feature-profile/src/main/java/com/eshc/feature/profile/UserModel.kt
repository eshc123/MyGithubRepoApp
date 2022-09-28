package com.eshc.feature.profile

import com.eshc.domain.model.User

data class UserModel(
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
    val following : Int,
)

fun User.toUserModel() : UserModel {
    return UserModel(
        id, login, avatarUrl, name, blog, location, email, bio, publicRepos, followers, following
    )
}
