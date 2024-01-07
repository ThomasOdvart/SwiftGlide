package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.network.ApiService

/**
 * Repository class responsible for handling authentication-related operations by interacting with [ApiService].
 *
 * @property apiService The API service used to make authentication-related API calls.
 */
class AuthRepository(private val apiService: ApiService) {

    /**
     * Performs user login by making a suspend function call to the corresponding API endpoint.
     *
     * @param email The user's email for login.
     * @param password The user's password for login.
     * @return A [LoginResponse] representing the result of the login operation.
     */
    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
    }

    /**
     * Performs user signup by making a suspend function call to the corresponding API endpoint.
     *
     * @param email The user's email for signup.
     * @param password The user's password for signup.
     * @param organization The organization name for signup.
     * @return A [SignupResponse] representing the result of the signup operation.
     */
    suspend fun signup(email: String, password: String, organization: String): SignupResponse {
        return apiService.signup(email, password, organization)
    }
}

