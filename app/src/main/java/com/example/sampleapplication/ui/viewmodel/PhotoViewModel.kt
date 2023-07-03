package com.example.sampleapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.data.room.ImageEntity
import com.example.sampleapplication.domain.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _post: MutableStateFlow<List<ImageEntity>> = MutableStateFlow(emptyList())
    val post = _post.asStateFlow()

    init {
        getPhoto()
    }

    private fun getPhoto() {
        repository.getAllPhoto()
            .distinctUntilChanged()
            .onEach {
                _post.value = it
            }.launchIn(viewModelScope)
    }

    fun insertPhotoToDb(photo : List<ImageEntity>){
        viewModelScope.launch {
            repository.insertPhotos(photo)
        }
    }
}