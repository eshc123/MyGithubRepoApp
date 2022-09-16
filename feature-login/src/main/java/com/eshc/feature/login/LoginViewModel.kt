package com.eshc.feature.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eshc.domain.usecase.GetAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState : LiveData<LoginUiState>
        get() = _uiState

    fun getAccessToken(code : String) {
        getAccessTokenUseCase(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                if(result.isSuccess) {
                    updateHasAccessToken(true)
                } else {
                    updateHasAccessToken(false)
                    updateIsLoading(false)
                    updateError(error.message.toString())
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