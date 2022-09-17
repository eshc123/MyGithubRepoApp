package com.eshc.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _uiState = MutableLiveData(HomeUiState())
    val uiState : LiveData<HomeUiState>
        get() = _uiState

    fun updateSelectedTab(tabName : String) {
        _uiState.value = uiState.value?.copy(
            selectedTab = HomeTab.valueOf(tabName)
        )
    }
}