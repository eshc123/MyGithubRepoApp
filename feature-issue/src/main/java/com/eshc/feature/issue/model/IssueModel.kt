package com.eshc.feature.issue.model

import com.eshc.domain.model.Issue
import com.eshc.domain.model.Repo

data class IssueModel(
    val id : Int,
    val repo : Repo,
    val state : String,
    val title : String,
    val updatedAt : String,
    val isOpen : Boolean
)

fun Issue.toIssueModel() : IssueModel {
    return IssueModel(
        id = id,
        repo = repo,
        state = state.name,
        title = title,
        updatedAt = updatedAt,
        isOpen = false
    )
}
