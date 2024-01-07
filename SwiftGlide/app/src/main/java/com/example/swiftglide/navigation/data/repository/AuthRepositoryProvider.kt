package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.network.RetrofitClient

/**
 * Singleton object responsible for providing a single instance of [AuthRepository] throughout the application.
 */
object AuthRepositoryProvider {

    /**
     * The singleton instance of [AuthRepository].
     */
    private var authRepository: AuthRepository? = null

    /**
     * Provides an instance of [AuthRepository], creating one if it doesn't exist.
     *
     * @return The [AuthRepository] instance.
     */
    fun provideAuthRepository(): AuthRepository {
        if (authRepository == null) {
            authRepository = AuthRepository(RetrofitClient.apiService)
        }
        return authRepository!!
    }
}