package com.eshc.feature.login

import android.util.Log
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

    fun getAccessToken(code : String) {
        getAccessTokenUseCase(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result, error ->
                if(result.isSuccess) {
                    Log.d("accessToken",result.getOrDefault("accessToken"))
                    //TODO
                } else {
                    Log.d("accessToken","error")
                    //TODO
                }
        }
    }
}