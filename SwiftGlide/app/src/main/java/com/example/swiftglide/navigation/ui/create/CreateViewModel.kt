package com.example.swiftglide.navigation.ui.create

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swiftglide.navigation.data.model.CreatePlayerResponse
import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.ListTeam
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.Player
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.data.repository.AuthRepository
import com.example.swiftglide.navigation.data.repository.AuthRepositoryProvider
import com.example.swiftglide.navigation.data.repository.CreateRepository
import com.example.swiftglide.navigation.data.repository.CreateRepositoryProvider
import com.example.swiftglide.navigation.ui.auth.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for handling operations related to creating and retrieving teams.
 *
 * @property createRepository Repository responsible for interacting with the data source for team operations.
 */
class CreateViewModel(private val createRepository: CreateRepository) : ViewModel() {

    // MutableLiveData for creating a new team operation
    private val _createTeamResponse = MutableStateFlow(CreateTeamResponse(false, "", Team(0, "", emptyList())))
    val createTeamResponse: StateFlow<CreateTeamResponse> = _createTeamResponse

    /**
     * Creates a new team with the provided name and email.
     *
     * @param name The name of the team to be created.
     * @param email The email associated with the team.
     */
    fun createTeam(name: String, email: String) {
        viewModelScope.launch {
            Log.d("namecheck", "create team: ${name}")
            var result = createRepository.createTeam(name, email)
            _createTeamResponse.value = result
        }
    }


    private val _createPlayerResponse = MutableStateFlow(CreatePlayerResponse(false, "", Player(0, "", "")))
    val createPlayerResponse: StateFlow<CreatePlayerResponse> = _createPlayerResponse
    fun createPlayer(teamId: Int, email: String, password: String) {
        viewModelScope.launch {
            var result = createRepository.createPlayer(teamId, email, password)
            _createPlayerResponse.value = result
        }
    }

    // MutableLiveData for getting teams by organization operation
    private val _getTeamsResponse = MutableStateFlow<List<ListTeam>>(emptyList())
    val getTeamsResponse: MutableStateFlow<List<ListTeam>> = _getTeamsResponse

    /**
     * Retrieves a list of teams associated with the provided organization email.
     *
     * @param email The email of the organization to retrieve teams for.
     */
    fun getTeamsByOrganization(email: String) {
        viewModelScope.launch {
            var result = createRepository.getTeamsByOrganization(email)
            Log.d("create", "create team: ${result}")
            _getTeamsResponse.value = result
        }
    }

    // MutableLiveData for getting team by ID operation
    private val _getTeamResponse = MutableStateFlow(Team(0, "", emptyList()))
    val getTeamResponse: MutableStateFlow<Team> = _getTeamResponse

    /**
     * Retrieves a team with the provided ID.
     *
     * @param id The ID of the team to retrieve.
     */
    fun getTeamById(id: Int) {
        viewModelScope.launch {
            var result = createRepository.getTeamById(id)
            Log.d("ieoifefj", "getTeamById: ${result}")
            _getTeamResponse.value = result
        }
    }

    /**
     * Companion object to provide a ViewModel factory.
     */
    companion object {
        /**
         * Creates a factory for the [CreateViewModel].
         *
         * @return An instance of [ViewModelProvider.Factory] for creating [CreateViewModel] instances.
         */
        fun createFactory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(CreateViewModel::class.java)) {
                        val createRepository = CreateRepositoryProvider.provideCreateRepository()
                        return CreateViewModel(createRepository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}