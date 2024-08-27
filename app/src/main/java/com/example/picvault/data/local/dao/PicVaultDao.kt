package com.example.picvault.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.picvault.data.model.ImageEntity

@Dao
interface PicVaultDao {
    @Insert
   suspend fun insertImage(imageEntity: ImageEntity)

    @Query("SELECT * FROM imageentity")
   suspend fun fetchAllImage(): List<ImageEntity>
}