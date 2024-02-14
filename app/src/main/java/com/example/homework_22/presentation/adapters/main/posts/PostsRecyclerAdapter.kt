package com.example.homework_22.presentation.adapters.main.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ItemLayoutPostBinding
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.model.main.posts.PostsModel
import com.example.homework_22.presentation.util.DateUtil

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

    override fun onBindViewHolder(holder: PostsRecyclerAdapter.PostsViewHolder, position: Int) {
        holder.bind()
    }

    inner class PostsViewHolder(private val binding: ItemLayoutPostBinding) :
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
                tvDate.text = DateUtil.epochToReadableDate(item.owner.postDate)
                tvTitle.text = item.title
                "${item.comments} Comments".also { tvMessages.text = it }
                "${item.likes} Likes".also { tvLikes.text = it }

//                item.images?.let {
//                    if (it.isNotEmpty()) {
//                        ivCover1.loadImage(it[0])
//                    }
//                    if (it.size > 1) {
//                        ivCover2.loadImage(it[1])
//                    }
//                    if (it.size > 2) {
//                        ivCover3.loadImage(it[2])
//                    }
//                }
                Log.d("TAG", "bind: ${item.images?.size}")
                when (item.images?.size) {
                    1 -> {
                    Log.d("TAG", "bind: ${item.images?.size}")
                        ivCover1.isVisible = true
                        ivCover1.loadImage(item.images?.get(0))
                    }

                    2 -> {
                        Log.d("TAG", "bind: ${item.images?.size}")
                        ivCover1.isVisible = true
                        ivCover2.isVisible = true
                        ivCover1.loadImage(item.images?.get(0))
                        ivCover2.loadImage(item.images?.get(1))
                    }

                    3 -> {
                        Log.d("TAG", "bind: ${item.images?.size}")
                        ivCover1.isVisible = true
                        ivCover2.isVisible = true
                        ivCover3.isVisible = true
                        ivCover1.loadImage(item.images?.get(0))
                        ivCover2.loadImage(item.images?.get(1))
                        ivCover3.loadImage(item.images?.get(2))
                    }
                    null -> {
                        Log.d("TAG", "bind: ${item.images?.size}")
                        ivCover1.isVisible = false
                        ivCover2.isVisible = false
                        ivCover3.isVisible = false
                    }
                }
            }
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