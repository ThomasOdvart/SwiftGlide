package com.example.swiftglide.navigation.data.repository

import android.util.Log
import com.example.swiftglide.navigation.data.model.CreatePlayerResponse
import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.ListTeam
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.data.network.ApiService

/**
 * Repository class responsible for handling data operations related to team creation and retrieval.
 *
 * @param apiService The [ApiService] used for making network requests.
 */
class CreateRepository(private val apiService: ApiService) {

    /**
     * Creates a team with the specified [name] and [email].
     *
     * @param name The name of the team to be created.
     * @param email The email associated with the team.
     * @return A [CreateTeamResponse] indicating the result of the team creation.
     */
    suspend fun createTeam(name: String, email: String): CreateTeamResponse {
        Log.d("checkname", "createTeam: ${name}")
        return apiService.createTeam(name, email)
    }

    /**
     * Retrieves a list of teams associated with the specified [email].
     *
     * @param email The email used to identify teams associated with an organization.
     * @return A list of [ListTeam] objects representing teams.
     */
    suspend fun getTeamsByOrganization(email: String): List<ListTeam> {
        return apiService.getTeamsByOrganization(email)
    }

    /**
     * Retrieves a team by its unique [id].
     *
     * @param id The unique identifier of the team.
     * @return A [Team] object representing the team with the specified [id].
     */
    suspend fun getTeamById(id: Int): Team {
        return apiService.getTeamById(id)
    }



    suspend fun createPlayer(teamId: Int, email: String, password: String) : CreatePlayerResponse {
        return apiService.createPlayerInTeam(teamId, email, password)
    }
}