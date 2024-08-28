package com.example.picvault.domain.repository

import com.example.picvault.domain.Image

interface PicRepository {
   suspend fun saveImage(image: Image)
   suspend fun getAllImages(): List<Image>
}