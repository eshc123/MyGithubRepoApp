package com.eshc.feature.issue.model

import com.eshc.domain.model.Repo

data class IssueModel(
    val id : Int,
    val repo : Repo,
    val state : String,
    val title : String,
    val updatedAt : String,
    val isOpen : Boolean
)
