package com.example.picvault.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var uri: Url,
    var timeStamp: Long
)