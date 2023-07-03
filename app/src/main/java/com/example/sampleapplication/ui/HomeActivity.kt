package com.example.sampleapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.data.room.AddImageDao
import com.example.sampleapplication.data.room.ImageEntity
import com.example.sampleapplication.ui.viewmodel.PhotoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private var fabButton: FloatingActionButton? = null

    private var imageList = arrayListOf<ImageEntity>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageViewAdapter: ImageViewAdapter
    private val viewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        fabButton = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)
        fabButton!!.setOnClickListener {
            imagePicker.launch("image/*")
        }


        setupData()


    }

    private fun setupData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.post.collect {

                    imageViewAdapter = ImageViewAdapter(it)
                    recyclerView.adapter = imageViewAdapter
                    imageViewAdapter.notifyDataSetChanged()
                }
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            it?.let {
                if (it.isNotEmpty()) {
                    for (image in it) {
                        imageList.add(ImageEntity(imageUri = image.toString()))
                    }
                }
                if (imageList.isNotEmpty()) run {
                    viewModel.insertPhotoToDb(imageList)
                }
            }
        }
}