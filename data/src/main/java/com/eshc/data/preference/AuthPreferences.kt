package com.eshc.data.preference

import android.content.Context
import androidx.preference.PreferenceManager

class AuthPreferences(
    context : Context
) {
    companion object {
        private const val ACCESS_TOKEN = "access_token"
    }

    private val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    var accessToken
        get() = prefs.getString(ACCESS_TOKEN, null)
        set(value) = prefs.edit().putString(ACCESS_TOKEN, value).apply()
}