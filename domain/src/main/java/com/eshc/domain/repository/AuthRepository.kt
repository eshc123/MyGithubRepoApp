package com.eshc.domain.repository

interface AuthRepository {
    fun getAccessToken() : String
}