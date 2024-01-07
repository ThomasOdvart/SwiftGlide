package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.network.ApiService

class AuthRepository(private val apiService: ApiService) {
    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
    }

    suspend fun signup(email: String, password: String, organization: String): SignupResponse {
        return apiService.signup(email, password, organization)
    }
}

