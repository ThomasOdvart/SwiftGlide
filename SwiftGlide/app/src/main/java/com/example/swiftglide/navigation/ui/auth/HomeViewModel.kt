package com.example.swiftglide.navigation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swiftglide.navigation.data.model.User
import com.example.swiftglide.navigation.data.repository.AuthRepositoryProvider
import com.example.swiftglide.navigation.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class for managing user-related operations in the home screen.
 *
 * @property userRepository Repository for managing user data.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    /**
     * LiveData holding a list of all users.
     */
    val userList: LiveData<List<User>> = userRepository.allUsers

    /**
     * LiveData holding a single user found by email.
     */
    val foundUser: LiveData<User> = userRepository.foundUser

    /**
     * Finds a user by email using [userRepository].
     *
     * @param email The email of the user to be found.
     */
    fun findUserByEmail(email: String) {
        userRepository.findUserByEmail(email)
    }

    /**
     * Retrieves all users using [userRepository].
     */
    fun getAllUsers() {
        userRepository.getAllUsers()
    }

    /**
     * Adds a new user using [userRepository] and refreshes the list of all users.
     *
     * @param user The user to be added.
     */
    fun addUser(user: User) {
        userRepository.addUser(user)
        getAllUsers()
    }

    /**
     * Updates user details using [userRepository] and refreshes the list of all users.
     *
     * @param user The updated user details.
     */
    fun updateUserDetails(user: User) {
        userRepository.updateUserDetails(user)
        getAllUsers()
    }

    /**
     * Deletes the specified user using [userRepository] and then refreshes the list of all users.
     *
     * @param user The user to be deleted.
     */
    fun deleteUser(user: User) {
        // Delete the user using the userRepository
        userRepository.deleteUser(user)

        // Refresh the list of all users after deletion
        getAllUsers()
    }

}