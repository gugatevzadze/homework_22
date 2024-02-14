package com.example.homework_22.presentation.adapters.main.posts

import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.model.main.posts.PostsModel

class ImagesRecyclerAdapter : ListAdapter<PostsModel, ImagesRecyclerAdapter.ImagesViewHolder>(ImagesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val imageView = ImageView(parent.context)
        return ImagesViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        Log.d("CustomAdapter", "Binding item at position: $position")
        getItem(position).images?.let { holder.bind(it) }
    }

    inner class ImagesViewHolder(private val imageView: ImageView) :
        RecyclerView.ViewHolder(imageView) {
        fun bind(images: List<String>) {
            Log.d("CustomAdapter", "Loading image: ${images[0]}")
            imageView.loadImage(images[0])
        }
    }

    private class ImagesDiffCallback : DiffUtil.ItemCallback<PostsModel>() {
        override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem:PostsModel, newItem: PostsModel): Boolean {
            return oldItem == newItem
        }
    }
}
