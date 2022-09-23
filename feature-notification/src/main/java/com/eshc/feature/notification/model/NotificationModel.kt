package com.eshc.feature.notification.model

import com.eshc.domain.model.Notification

data class NotificationModel(
    val id : String,
    val title : String,
    val repoFullName : String,
    val imageUrl : String,
    val updatedAt : String,
    val comments : String,
    val issueNum : String
)

fun Notification.toNotificationModel() : NotificationModel {
    return NotificationModel(
        id = id,
        title = repo.name,
        repoFullName = repo.fullName,
        imageUrl = "",
        updatedAt = updatedAt,
        comments = comments.toString(),
        issueNum = issueNum.toString()
    )
}
