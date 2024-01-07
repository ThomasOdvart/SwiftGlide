package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.network.RetrofitClient

object CreateRepositoryProvider {
    private var createRepository: CreateRepository? = null

    fun provideCreateRepository(): CreateRepository {
        if (createRepository == null) {
            createRepository = CreateRepository(RetrofitClient.apiService)
        }
        return createRepository!!
    }
}