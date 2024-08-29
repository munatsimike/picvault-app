package com.example.picvault.domain.useCase

import com.example.picvault.domain.Image

interface SaveImageUseCase {
    fun saveImageToGallery(image: Image): Result<Unit>
}