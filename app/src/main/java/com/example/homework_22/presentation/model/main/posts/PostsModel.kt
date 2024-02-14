package com.example.homework_22.presentation.model.main.posts

import com.example.homework_22.domain.model.stories.Owner

data class PostsModel(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: OwnerModel
)