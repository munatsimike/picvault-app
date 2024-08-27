package com.example.picvault.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: Url,
    val timeStamp: Long
)