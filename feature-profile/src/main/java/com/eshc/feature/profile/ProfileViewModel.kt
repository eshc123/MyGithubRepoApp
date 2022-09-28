package com.eshc.feature.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eshc.domain.usecase.user.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(ProfileUiState())
    val uiState : LiveData<ProfileUiState>
        get() = _uiState

    fun getUser(){
        getUserProfileUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                if(result.isSuccess){
                    result.getOrThrow().let {
                        _uiState.value = uiState.value?.copy(
                            user = it.toUserModel()
                        )
                    }
                }
                else {

                }
            }
    }
}