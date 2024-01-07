package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.network.RetrofitClient

object AuthRepositoryProvider {
    private var authRepository: AuthRepository? = null

    fun provideAuthRepository(): AuthRepository {
        if (authRepository == null) {
            authRepository = AuthRepository(RetrofitClient.apiService)
        }
        return authRepository!!
    }
}