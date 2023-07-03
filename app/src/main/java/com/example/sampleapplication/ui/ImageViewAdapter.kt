package com.example.sampleapplication.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.data.room.ImageEntity

class ImageViewAdapter(private val imageList : List<ImageEntity>) : RecyclerView.Adapter<ImageViewAdapter.MyImageViewHolder>() {

    inner class MyImageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val imageView : ImageView = itemView.findViewById(R.id.imageView)
        fun bindImage(position: Int) {
            imageView.setImageURI(Uri.parse(imageList[position].imageUri))
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_layout,parent,false)
        return MyImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyImageViewHolder, position: Int) {
        holder.bindImage(position)
    }
}