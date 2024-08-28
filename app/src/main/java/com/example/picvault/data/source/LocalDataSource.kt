package com.example.picvault.data.source

import com.example.picvault.data.local.dao.PicVaultDao
import com.example.picvault.data.model.ImageEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val picVaultDao: PicVaultDao) {

    suspend fun saveImageEntity(imageEntity: ImageEntity) {
        picVaultDao.insertImageEntity(imageEntity)
    }

    suspend fun getAllImageEntities() = picVaultDao.getAllImageEntities()
}