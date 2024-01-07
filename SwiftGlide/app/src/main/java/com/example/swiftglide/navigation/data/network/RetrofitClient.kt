package com.example.swiftglide.navigation.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object responsible for creating and providing an instance of [ApiService] using Retrofit.
 */
object RetrofitClient {

    /**
     * Base URL for the API endpoints.
     */
    private const val BASE_URL = "https://firnenergybackend.onrender.com/api/android/"

    /**
     * Lazily initialized instance of [ApiService] using Retrofit.
     */
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}