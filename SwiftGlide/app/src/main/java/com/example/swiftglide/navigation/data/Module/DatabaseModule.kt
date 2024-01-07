package com.example.swiftglide.navigation.data.Module

import android.content.Context
import androidx.room.Room
import com.example.swiftglide.navigation.data.Database.UserDao
import com.example.swiftglide.navigation.data.Database.UserRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    /**
     * Provides a [UserDao] instance with [appDatabase] as a dependency.
     *
     * @param appDatabase The user room database.
     * @return Instance of [UserDao].
     */
    @Provides
    fun provideUserDao(appDatabase: UserRoomDatabase): UserDao {
        return appDatabase.userDao()
    }

    /**
     * Provides a singleton instance of [UserRoomDatabase].
     *
     * @param context The application context.
     * @return Singleton instance of [UserRoomDatabase].
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): UserRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            UserRoomDatabase::class.java,
            "appDB"
        ).build()
    }

}