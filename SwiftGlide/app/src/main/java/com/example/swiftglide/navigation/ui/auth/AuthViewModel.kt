package com.example.swiftglide.navigation.ui.auth

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.repository.AuthRepository
import com.example.swiftglide.navigation.data.repository.AuthRepositoryProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginResponse = MutableStateFlow(LoginResponse(validated = false, message = "", role = ""))
    val loginResponse: StateFlow<LoginResponse> = _loginResponse

    fun login(email: String, password: String) {
        viewModelScope.launch {
            var result = authRepository.login(email, password)
            Log.d("viewmodel", "login: ${result}")
            _loginResponse.value = result
        }
    }


    private val _signupResponse = MutableStateFlow(SignupResponse(false, ""))
    val signupResponse: StateFlow<SignupResponse> = _signupResponse

    fun signup(email: String, password: String, organization: String) {
        viewModelScope.launch {
            val response = authRepository.signup(email, password, organization)
            Log.d("viewmodel", "${response}")
            _signupResponse.value = response
        }
    }

    companion object {
        fun createFactory(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                        val authRepository = AuthRepositoryProvider.provideAuthRepository()
                        return AuthViewModel(authRepository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}