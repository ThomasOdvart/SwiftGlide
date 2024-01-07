package com.example.swiftglide.navigation.data.repository

import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.data.network.ApiService

class CreateRepository(private val apiService: ApiService) {
    suspend fun createTeam(name: String, amountOfPlayers: Int, email: String): CreateTeamResponse {
        return apiService.createTeam(name, amountOfPlayers, email)
    }
}