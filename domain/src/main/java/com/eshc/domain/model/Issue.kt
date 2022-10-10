package com.eshc.domain.model

data class Issue(
    val id : Int,
    val repo : Repo,
    val state : IssueState,
    val title : String,
    val updatedAt : String
)
