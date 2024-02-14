package com.example.homework_22.presentation.adapters.main.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ItemLayoutPostBinding
import com.example.homework_22.presentation.adapters.main.layout_manager.CustomLayoutManager
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.model.main.posts.PostsModel

class PostsRecyclerAdapter : ListAdapter<PostsModel, PostsRecyclerAdapter.PostsViewHolder>(
    PostsDiffCallback()
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsViewHolder {
        val binding =
            ItemLayoutPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
        val images = currentList[position].images
        if (images != null) {
            holder.setImages(images)
        }
    }

    inner class PostsViewHolder(val binding: ItemLayoutPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: PostsModel
        fun bind() {
            item = currentList[adapterPosition]
            binding.apply {
                tvUser.text = buildString {
                    append(item.owner.firstName)
                    append(" ")
                    append(item.owner.lastName)
                }
                ivProfile.loadImage(item.owner.profile)
                tvDate.text = item.owner.postDate
                tvTitle.text = item.title
                "${item.comments} Comments".also { tvMessages.text = it }
                "${item.likes} Likes".also { tvLikes.text = it }
            }
        }

        fun setImages(images: List<String>) {
            binding.rvImages.layoutManager = CustomLayoutManager(binding.root.context)
            binding.rvImages.adapter = ImagesRecyclerAdapter()
        }
    }

    private class PostsDiffCallback : DiffUtil.ItemCallback<PostsModel>() {
        override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostsModel, newItem: PostsModel): Boolean {
            return oldItem == newItem
        }
    }
}