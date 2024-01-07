package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.network.RetrofitClient

/**
 * Singleton provider for creating or retrieving an instance of [CreateRepository].
 */
object CreateRepositoryProvider {
    // A single instance of CreateRepository
    private var createRepository: CreateRepository? = null

    /**
     * Provides an instance of [CreateRepository], creating one if it doesn't exist.
     *
     * @return An instance of [CreateRepository].
     */
    fun provideCreateRepository(): CreateRepository {
        // If the instance doesn't exist, create one using RetrofitClient's ApiService
        if (createRepository == null) {
            createRepository = CreateRepository(RetrofitClient.apiService)
        }
        return createRepository!!
    }
}