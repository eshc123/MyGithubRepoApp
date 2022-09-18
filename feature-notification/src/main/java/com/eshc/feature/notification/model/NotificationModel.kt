package com.eshc.feature.notification.model

import com.eshc.domain.model.Repo

data class NotificationModel(
    val id : String,
    val title : String,
    val repo : Repo,
    val updatedAt : String,
    val comments : String,
    val issueNum : String
)