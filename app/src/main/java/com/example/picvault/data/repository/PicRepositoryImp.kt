package com.example.picvault.data.repository

import com.example.picvault.data.source.LocalDataSource
import com.example.picvault.data.toListOfImage
import com.example.picvault.domain.Image
import com.example.picvault.domain.repository.PicRepository
import com.example.picvault.domain.toImageEntity
import javax.inject.Inject

/**
 * The PicRepositoryImp class is responsible for managing data flow between the domain layer
 * and the data sources, such as the local database. It prepares data for the domain layer
 * by converting data models (ImageEntity) to domain models (Image) and vice versa.

 * - Ensures that the domain layer only interacts with Image objects, keeping it decoupled
 *   from the data layer.
 */

class PicRepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) :
    PicRepository {
    override suspend fun saveImage(image: Image) {
        localDataSource.saveImageEntity(image.toImageEntity())
    }

    override suspend fun getAllImages(): List<Image> {
        return localDataSource.getAllImageEntities().toListOfImage()
    }
}