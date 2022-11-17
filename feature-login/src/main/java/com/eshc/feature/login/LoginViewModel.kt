package com.eshc.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.domain.usecase.auth.GetAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState : LiveData<LoginUiState>
        get() = _uiState

    fun getAccessToken(code : String) {
        viewModelScope.launch {
            runCatching {
                getAccessTokenUseCase(code)
            }.onSuccess {
                updateHasAccessToken(true)
            }.onFailure {
                updateHasAccessToken(false)
                updateIsLoading(false)
                updateError() //TODO
            }
        }
    }

    fun updateIsLoading(isLoading : Boolean){
        _uiState.value = uiState.value?.copy(
            isLoading = isLoading
        )
    }

    private fun updateHasAccessToken(hasAccessToken : Boolean){
        _uiState.value = uiState.value?.copy(
            hasAccessToken = hasAccessToken
        )
    }

    fun updateError(errorMessage : String = ""){
        _uiState.value = uiState.value?.copy(
            error = errorMessage
        )
    }
}