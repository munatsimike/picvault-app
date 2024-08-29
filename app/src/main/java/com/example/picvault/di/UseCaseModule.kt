package com.example.picvault.di

import com.example.picvault.data.repository.PicRepositoryImp
import com.example.picvault.domain.useCase.SaveImageUseCase
import com.example.picvault.domain.useCase.SaveToGalleryUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSaveImageUseCase(picRepositoryImp: PicRepositoryImp): SaveImageUseCase =
        SaveToGalleryUseCaseImp(picRepositoryImp)
}