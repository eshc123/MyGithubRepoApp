package com.eshc.feature.login

data class LoginUiState (
    val hasAccessToken : Boolean = false,
    val isLoading : Boolean = false,
    val error : String = ""
)