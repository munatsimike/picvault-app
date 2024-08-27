package com.example.picvault.di

import android.content.Context
import androidx.room.Room
import com.example.picvault.data.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, LocalDatabase::class.java, "LocalDB").build()

    @Provides
    fun providePicVaultDao(localDatabase: LocalDatabase) = localDatabase.picVaultDao
}