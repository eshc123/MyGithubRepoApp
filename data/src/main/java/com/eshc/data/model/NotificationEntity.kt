package com.eshc.data.model

import com.eshc.domain.model.Notification
import com.google.gson.annotations.SerializedName

data class NotificationEntity(
    @SerializedName("id") val id : String,
    @SerializedName("repository") val repository : RepoEntity,
    @SerializedName("updated_at") val updatedAt : String,
    @SerializedName("unread") val unread : Boolean,
    @SerializedName("subject") val subject: NotificationSubjectEntity,
    @SerializedName("last_read_at") val lastReadAt : String,
    @SerializedName("url") val url : String
)

