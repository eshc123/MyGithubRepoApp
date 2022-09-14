package com.eshc.data.model

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token") val accessToken : String
)
