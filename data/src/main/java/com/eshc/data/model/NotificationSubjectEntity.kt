package com.eshc.data.model

import com.google.gson.annotations.SerializedName

data class NotificationSubjectEntity(
    @SerializedName("title") val title : String,
    @SerializedName("url") val url : String,
)