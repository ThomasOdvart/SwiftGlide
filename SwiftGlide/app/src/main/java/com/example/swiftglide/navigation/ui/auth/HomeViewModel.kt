package com.example.swiftglide.navigation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swiftglide.navigation.data.model.User
import com.example.swiftglide.navigation.data.repository.AuthRepositoryProvider
import com.example.swiftglide.navigation.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userList: LiveData<List<User>> = userRepository.allUsers
    val foundUser: LiveData<User> = userRepository.foundUser

    fun findUserByEmail(email: String) {
        userRepository.findUserByEmail(email)
    }

    fun getAllUsers(){
        userRepository.getAllUsers()
    }

    fun addUser(user: User) {
        userRepository.addUser(user)
        getAllUsers()
    }

    fun updateUserDetails(user: User) {
        userRepository.updateUserDetails(user)
        getAllUsers()
    }
}