package com.example.sampleapplication.di

import android.app.Application
import com.example.sampleapplication.data.room.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providePhotoDatabase(application: Application) = PhotoDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideCarDao(database: PhotoDatabase) = database.addImageDao()
}