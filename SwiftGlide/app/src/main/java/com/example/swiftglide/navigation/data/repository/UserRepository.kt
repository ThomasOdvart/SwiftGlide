package com.example.swiftglide.navigation.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.swiftglide.navigation.data.Database.UserDao
import com.example.swiftglide.navigation.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository (private val userDao: UserDao) {

    val allUsers = MutableLiveData<List<User>>()
    val foundUser = MutableLiveData<User>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addUser(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            Log.d("userrepo", "addUser: ran")
            userDao.addUser(newUser)
        }
    }

    fun getAllUsers() {
        coroutineScope.launch(Dispatchers.IO) {
            allUsers.postValue(userDao.getAllUsers())
        }
    }

    fun findUserByEmail(email: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundUser.postValue(userDao.findUserByEmail(email))
        }
    }

    fun updateUserDetails(newUser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.updateUserDetails(newUser)
        }
    }


}