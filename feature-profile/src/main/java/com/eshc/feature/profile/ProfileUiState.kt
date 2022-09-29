package com.eshc.feature.profile

import com.eshc.domain.model.User

data class ProfileUiState(
    val user : UserModel = User().toUserModel(),
    val error : String = ""
)
