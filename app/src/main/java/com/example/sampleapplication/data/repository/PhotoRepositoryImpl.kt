package com.example.sampleapplication.data.repository

import com.example.sampleapplication.data.room.AddImageDao
import com.example.sampleapplication.data.room.ImageEntity
import com.example.sampleapplication.domain.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val dao : AddImageDao
) : PhotoRepository {
    override fun getAllPhoto(): Flow<List<ImageEntity>> = dao.fetchImages()

    override suspend fun insertPhotos(photos: List<ImageEntity>) = dao.addImage(photos)
}