package com.eshc.feature.home

data class HomeUiState(
    val selectedTab : HomeTab = HomeTab.Issue,
    val userImage : String = ""
)
