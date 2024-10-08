package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.picvault.data.local.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestDatabaseModule {

    @Provides
    @Named("test")
    fun providesInMemoryDB(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java).allowMainThreadQueries()
            .build()
}