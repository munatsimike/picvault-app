package com.example.picvault.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.picvault.data.local.database.converters.UrlToStringConverter
import com.example.picvault.data.local.dao.PicVaultDao
import com.example.picvault.data.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
@TypeConverters(UrlToStringConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract val picVaultDao: PicVaultDao
}