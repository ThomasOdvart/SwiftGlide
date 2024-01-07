package com.example.swiftglide.navigation.ui.create

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swiftglide.navigation.data.model.CreateTeamResponse
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

    private val _createTeamResponse = MutableStateFlow(CreateTeamResponse(false, "", Team(0, "", 0)))
    val createTeamResponse: StateFlow<CreateTeamResponse> = _createTeamResponse

    fun createTeam(name: String, amountOfPlayers: Int, email: String) {
        viewModelScope.launch {
            var result = createRepository.createTeam(name, amountOfPlayers, email)
            Log.d("create", "create team: ${result}")
            _createTeamResponse.value = result
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