package com.eshc.data.model

import com.google.gson.annotations.SerializedName

data class AccessTokenEntity(
    @SerializedName("access_token") val accessToken : String
)
