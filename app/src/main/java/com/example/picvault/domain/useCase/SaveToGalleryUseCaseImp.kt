package com.example.picvault.domain.useCase

import com.example.picvault.domain.Image
import com.example.picvault.domain.repository.PicRepository
import javax.inject.Inject

class SaveToGalleryUseCaseImp @Inject constructor(private val repository: PicRepository) :
    SaveImageUseCase {
    override fun saveImageToGallery(image: Image): Result<Unit> {
        return repository.saveImageToGallery(image)
    }
}