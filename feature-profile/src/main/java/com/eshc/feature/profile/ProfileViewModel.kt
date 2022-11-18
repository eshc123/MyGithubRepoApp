package com.eshc.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.domain.usecase.user.GetUserWithStarredCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserWithStarredCountUseCase: GetUserWithStarredCountUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(ProfileUiState())
    val uiState : LiveData<ProfileUiState>
        get() = _uiState

    fun getUser(){
        viewModelScope.launch {
            runCatching {
                getUserWithStarredCountUseCase()
            }.onSuccess {
                it.getOrNull()?.let {
                    _uiState.value = uiState.value?.copy(
                        user = it.toUserModel()
                    )
                }
            }.onFailure {
                _uiState.value = uiState.value?.copy(
                    error = it.message ?: ""
                )
            }
        }
    }
}