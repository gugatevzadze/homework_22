package com.example.homework_22.presentation.adapters.main.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ItemLayoutPostBinding
import com.example.homework_22.presentation.adapters.main.custom_layout.CustomLayoutManager
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
    }

    inner class PostsViewHolder(private val binding: ItemLayoutPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: PostsModel
        fun bind() {
            item = currentList[adapterPosition]
            binding.apply {
                tvUser.text = buildString {
                    //write this inside of the model
                    append(item.owner.firstName)
                    append(" ")
                    append(item.owner.lastName)
                }
                ivProfile.loadImage(item.owner.profile)
                tvDate.text = item.owner.postDate
                tvTitle.text = item.title
                //change this to use string resources
                "${item.comments} Comments".also { tvMessages.text = it }
                "${item.likes} Likes".also { tvLikes.text = it }

                //declaring the inner adapter
                initializeImagesAdapter(binding, item)
            }
        }
    }

    private fun initializeImagesAdapter(binding: ItemLayoutPostBinding, item: PostsModel) {
        val imagesAdapter = ImagesRecyclerAdapter()
        imagesAdapter.submitList(item.images)
        binding.rvImages.layoutManager = CustomLayoutManager()
        binding.rvImages.adapter = imagesAdapter
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