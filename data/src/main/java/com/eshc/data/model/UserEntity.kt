package com.eshc.data.model

import com.eshc.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("login") val login : String,
    @SerializedName("id") val id : Long,
    @SerializedName("avatar_url") val avatarUrl : String,
    @SerializedName("name") val name : String?,
    @SerializedName("blog") val blog : String?,
    @SerializedName("location") val location : String?,
    @SerializedName("email") val email : String?,
    @SerializedName("bio") val bio : String?,
    @SerializedName("public_repos") val publicRepos : Int,
    @SerializedName("followers") val followers : Int,
    @SerializedName("following") val following : Int
)

fun UserEntity.toUser() : User {
    return User(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        name = name ?: "",
        blog = blog ?: "",
        location = location ?: "",
        email = email ?: "",
        bio = bio ?: "",
        publicRepos = publicRepos,
        followers = followers,
        following = following
    )
}
