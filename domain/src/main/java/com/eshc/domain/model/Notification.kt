package com.eshc.domain.model

data class Notification(
    val id : Int,
    val repo : Repo,
    val updatedAt : String,
    val unread : Boolean,
    val comments: Int,
    val issueNum : Int
)