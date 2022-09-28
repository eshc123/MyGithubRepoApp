package com.eshc.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eshc.domain.usecase.user.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData(HomeUiState())
    val uiState : LiveData<HomeUiState>
        get() = _uiState

    init {
        if(uiState.value?.userImage.isNullOrBlank())
            getUser()
    }

    fun updateSelectedTab(tabName : String) {
        _uiState.value = uiState.value?.copy(
            selectedTab = HomeTab.valueOf(tabName)
        )
    }

    private fun updateUserImage(imageUrl : String) {
        _uiState.value = uiState.value?.copy(
            userImage = imageUrl
        )
    }

    private fun getUser() {
        getUserProfileUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                updateUserImage(result.getOrThrow().avatarUrl)
            }
    }

}