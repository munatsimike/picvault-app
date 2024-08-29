package com.example.picvault.di

import com.example.picvault.data.helper.ImageConverter
import com.example.picvault.data.repository.PicRepositoryImp
import com.example.picvault.data.source.GalleryManager
import com.example.picvault.data.source.LocalDataSource
import com.example.picvault.domain.repository.PicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePicRepository(
        imageConverter: ImageConverter,
        localDataSource: LocalDataSource,
        galleryManager: GalleryManager
    ): PicRepository = PicRepositoryImp(imageConverter, localDataSource, galleryManager)
}