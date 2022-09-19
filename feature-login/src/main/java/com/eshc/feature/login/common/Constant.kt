package com.eshc.feature.login.common

import com.eshc.feature.login.BuildConfig

const val GITHUB_AUTH = "https://github.com/login/oauth/authorize?client_id=${BuildConfig.githubClientId}&scope=user+repo"
