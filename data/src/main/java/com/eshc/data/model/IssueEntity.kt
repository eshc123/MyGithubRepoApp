package com.eshc.data.model

import com.eshc.domain.model.Issue
import com.eshc.domain.model.IssueState
import com.google.gson.annotations.SerializedName

data class IssueEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("repository") val repo : RepoEntity,
    @SerializedName("state") val state : String,
    @SerializedName("title") val title : String,
    @SerializedName("updated_at") val updatedAt : String,
)

fun IssueEntity.toIssue() : Issue {
    return Issue(
        id = id,
        repo = repo.toRepo(),
        state = IssueState.valueOf(state),
        title = title,
        updatedAt = updatedAt
    )
}