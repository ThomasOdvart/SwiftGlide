package com.example.swiftglide.navigation.ui.create

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.ListTeam
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.data.repository.AuthRepository
import com.example.swiftglide.navigation.data.repository.AuthRepositoryProvider
import com.example.swiftglide.navigation.data.repository.CreateRepository
import com.example.swiftglide.navigation.data.repository.CreateRepositoryProvider
import com.example.swiftglide.navigation.ui.auth.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateViewModel(private val createRepository: CreateRepository) : ViewModel() {

    private val _createTeamResponse = MutableStateFlow(CreateTeamResponse(false, "", Team(0, "", emptyList())))
    val createTeamResponse: StateFlow<CreateTeamResponse> = _createTeamResponse

    fun createTeam(name: String, email: String) {
        viewModelScope.launch {
            Log.d("namecheck", "create team: ${name}")
            var result = createRepository.createTeam(name, email)
            _createTeamResponse.value = result
        }
    }

    private val _getTeamsResponse = MutableStateFlow<List<ListTeam>>(emptyList())
    val getTeamsResponse: MutableStateFlow<List<ListTeam>> = _getTeamsResponse

    fun getTeamsByOrganization(email: String) {
        viewModelScope.launch {
            var result = createRepository.getTeamsByOrganization(email)
            Log.d("create", "create team: ${result}")
            _getTeamsResponse.value = result
        }
    }

    private val _getTeamResponse = MutableStateFlow(Team(0, "", emptyList()))
    val getTeamResponse: MutableStateFlow<Team> = _getTeamResponse

    fun getTeamById(id: Int) {
        viewModelScope.launch {
            var result = createRepository.getTeamById(id)
            Log.d("ieoifefj", "getTeamById: ${result}")
            _getTeamResponse.value = result
        }
    }

    companion object {
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