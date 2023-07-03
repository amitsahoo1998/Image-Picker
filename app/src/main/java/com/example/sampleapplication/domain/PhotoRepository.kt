package com.example.sampleapplication.domain

import com.example.sampleapplication.data.room.ImageEntity
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun getAllPhoto() : Flow<List<ImageEntity>>

    suspend fun insertPhotos(photos : List<ImageEntity>)
}