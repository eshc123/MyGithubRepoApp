package com.eshc.domain.model

data class Notification(
    val id : String,
    val repo : Repo,
    val title : String,
    val updatedAt : String,
    val unread : Boolean,
    val imageUrl : String,
    val comments: Int,
    val issueNum : Int
)
