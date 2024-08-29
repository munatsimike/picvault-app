package com.example.picvault.domain.repository

import android.net.Uri
import com.example.picvault.domain.Image

interface PicRepository {
    suspend fun saveImageToLocalDb(image: Image)
    fun saveImageToGallery(image: Image): Result<Unit>
    suspend fun getAllImages(): List<Image>
}