package com.example.picvault.di

import android.content.Context
import androidx.room.Room
import com.example.picvault.data.local.dao.PicVaultDao
import com.example.picvault.data.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase =
        Room.databaseBuilder(context, LocalDatabase::class.java, "local_db").build()

    @Singleton
    @Provides
    fun providePicVaultDao(localDatabase: LocalDatabase): PicVaultDao = localDatabase.picVaultDao
}