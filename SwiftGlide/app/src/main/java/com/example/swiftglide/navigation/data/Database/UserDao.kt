package com.example.swiftglide.navigation.data.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.swiftglide.navigation.data.model.User

/**
 * Data Access Object (DAO) for user-related database operations.
 */
@Dao
interface UserDao {

    /**
     * Inserts a new user into the database. If a user with the same email already exists,
     * the new user is ignored.
     *
     * @param user The user to be added.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    /**
     * Retrieves a user from the database based on their email address.
     *
     * @param userEmail The email address of the user.
     * @return The user with the specified email address.
     */
    @Query("SELECT * FROM users WHERE email = :userEmail")
    fun findUserByEmail(userEmail: String) : User

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users in the database.
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    /**
     * Updates the details of an existing user in the database.
     *
     * @param user The user with updated details.
     */
    @Update
    suspend fun updateUserDetails(user: User)

    /**
     * Deletes a user from the database.
     *
     * @param user The user to be deleted.
     */
    @Delete
    suspend fun deleteUser(user: User)


}