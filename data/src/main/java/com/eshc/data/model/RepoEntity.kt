package com.eshc.data.model

import com.google.gson.annotations.SerializedName

data class RepoEntity(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    //@SerializedName("owner") val owner : RepoOwner,
    @SerializedName("description") val description : String,
    @SerializedName("stargazers_count") val stargazersCount : Int,
    @SerializedName("language") val language : String,
    @SerializedName("full_name") val fullName : String
)