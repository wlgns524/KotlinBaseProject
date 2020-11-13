package com.rightcode.baseproject.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.rightcode.baseproject.Features.DATABASE_NAME
import com.rightcode.baseproject.data.local.LocalDatabase
import com.rightcode.baseproject.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalDatabaseModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(localDatabase: LocalDatabase): UserDao {
        return localDatabase.getUserDao()
    }
}