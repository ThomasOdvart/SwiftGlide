package com.example.swiftglide.navigation.data.Module

import com.example.swiftglide.navigation.data.Database.UserDao
import com.example.swiftglide.navigation.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing application-level dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [UserRepository] with [userDao] as a dependency.
     *
     * @param userDao The data access object for user-related database operations.
     * @return Singleton instance of [UserRepository].
     */
    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

}