package com.example.homework_22.presentation.adapters.main.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ItemLayoutImagesBinding
import com.example.homework_22.presentation.extension.loadImage

class ImagesRecyclerAdapter : ListAdapter<String, ImagesRecyclerAdapter.ImagesViewHolder>(ImagesDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesRecyclerAdapter.ImagesViewHolder {
        val binding =
            ItemLayoutImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind()
    }

    inner class ImagesViewHolder(private val binding: ItemLayoutImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var imageUrl: String
        fun bind() {
            imageUrl = currentList[adapterPosition]
            binding.apply {
                ivImage.loadImage(imageUrl)
            }
        }
    }

    private class ImagesDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
