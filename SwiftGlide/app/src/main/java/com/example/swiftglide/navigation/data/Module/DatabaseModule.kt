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

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {

    @Provides
    fun provideUserDao(appDatabase: UserRoomDatabase): UserDao {
        return appDatabase.userDao()
    }

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