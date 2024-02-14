package com.example.homework_22.presentation.adapters.main.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ItemLayoutStoryBinding
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.model.main.stories.StoriesModel

class StoriesRecyclerAdapter: ListAdapter<StoriesModel, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val binding = ItemLayoutStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind()
    }

    inner class StoriesViewHolder(private val binding: ItemLayoutStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: StoriesModel
        fun bind() {
            item = currentList[adapterPosition]
            binding.apply {
                tvTitle.text = item.title
                ivCover.loadImage(item.cover)
            }
        }
    }

    private class StoriesDiffCallback : DiffUtil.ItemCallback<StoriesModel>() {
        override fun areItemsTheSame(oldItem: StoriesModel, newItem: StoriesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoriesModel, newItem: StoriesModel): Boolean {
            return oldItem == newItem
        }
    }
}