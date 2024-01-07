package com.example.swiftglide.navigation.data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swiftglide.navigation.data.model.User

/**
 * Room Database class for managing user-related data.
 *
 * @property userDao Instance of [UserDao] for performing database operations on user data.
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        /**
         * Volatile variable to ensure that changes to INSTANCE are immediately visible to other threads.
         */
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        /**
         * Gets the singleton instance of [UserRoomDatabase].
         *
         * @param context The application context.
         * @return The singleton instance of [UserRoomDatabase].
         */
        fun getInstance(context: Context): UserRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserRoomDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}