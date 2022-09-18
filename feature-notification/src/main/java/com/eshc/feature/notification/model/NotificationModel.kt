package com.eshc.feature.notification.model

data class NotificationModel(
    val id : String,
    val title : String,
    val repoFullName : String,
    val imageUrl : String,
    val updatedAt : String,
    val comments : String,
    val issueNum : String
)