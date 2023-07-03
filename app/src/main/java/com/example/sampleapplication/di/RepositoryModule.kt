package com.example.sampleapplication.di

import com.example.sampleapplication.data.repository.PhotoRepositoryImpl
import com.example.sampleapplication.data.room.AddImageDao
import com.example.sampleapplication.domain.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providePhotoRepository(dao: AddImageDao): PhotoRepository{
        return PhotoRepositoryImpl(dao)
    }

}