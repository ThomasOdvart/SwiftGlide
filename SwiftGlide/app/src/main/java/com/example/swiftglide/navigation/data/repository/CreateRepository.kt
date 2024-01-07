package com.example.swiftglide.navigation.data.repository

import android.util.Log
import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.ListTeam
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.data.network.ApiService

class CreateRepository(private val apiService: ApiService) {
    suspend fun createTeam(name: String, email: String): CreateTeamResponse {
        Log.d("checkname", "createTeam: ${name}")
        return apiService.createTeam(name, email)
    }

    suspend fun getTeamsByOrganization(email: String) : List<ListTeam> {
        return apiService.getTeamsByOrganization(email)
    }

    suspend fun getTeamById(id: Int) : Team {
        return apiService.getTeamById(id)
    }


}