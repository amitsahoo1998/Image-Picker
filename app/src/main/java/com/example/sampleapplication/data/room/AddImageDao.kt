package com.example.sampleapplication.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AddImageDao {

    @Insert
    suspend fun addImage(imageList : List<ImageEntity>)

    @Query("SELECT * FROM ImageEntity")
    fun fetchImages() : Flow<List<ImageEntity>>
}