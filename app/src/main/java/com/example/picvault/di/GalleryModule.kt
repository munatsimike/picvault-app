package com.example.picvault.di

import android.content.Context
import com.example.picvault.data.helper.ImageConverter
import com.example.picvault.data.source.GalleryManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GalleryModule {

    @Singleton
    @Provides
    fun provideGalleryManager(@ApplicationContext context: Context): GalleryManager =
        GalleryManager(context)

    @Singleton
    @Provides
    fun provideImageConverter(@ApplicationContext context: Context): ImageConverter =
        ImageConverter(context)
}