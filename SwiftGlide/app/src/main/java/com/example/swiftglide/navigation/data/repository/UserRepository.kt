package com.example.swiftglide.navigation.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.swiftglide.navigation.data.Database.UserDao
import com.example.swiftglide.navigation.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Repository class for managing user data. Uses [userDao] to interact with the underlying database.
 *
 * @property userDao DAO (Data Access Object) for user-related database operations.
 */
class UserRepository(private val userDao: UserDao) {

    /**
     * LiveData holding a list of all users.
     */
    val allUsers = MutableLiveData<List<User>>()

    /**
     * LiveData holding a single user found by email.
     */
    val foundUser = MutableLiveData<User>()

    // Coroutine scope for performing asynchronous operations
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    /**
     * Adds a new user to the database in a background thread.
     *
     * @param newUser The user to be added.
     */
    fun addUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            Log.d("userrepo", "addUser: ran")
            userDao.addUser(newUser)
        }
    }

    /**
     * Retrieves all users from the database in a background thread and updates [allUsers] LiveData.
     */
    fun getAllUsers() {
        coroutineScope.launch(Dispatchers.IO) {
            allUsers.postValue(userDao.getAllUsers())
        }
    }

    /**
     * Finds a user by email in the database in a background thread and updates [foundUser] LiveData.
     *
     * @param email The email of the user to be found.
     */
    fun findUserByEmail(email: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundUser.postValue(userDao.findUserByEmail(email))
        }
    }

    /**
     * Updates user details in the database in a background thread.
     *
     * @param newUser The updated user details.
     */
    fun updateUserDetails(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.updateUserDetails(newUser)
        }
    }

    /**
     * Deletes a user from the database in a background thread.
     *
     * @param user The user to be deleted.
     */
    fun deleteUser(user: User) {
        coroutineScope.launch(Dispatchers.IO) {
            // Delete the user from the database
            userDao.deleteUser(user)
        }
    }

}