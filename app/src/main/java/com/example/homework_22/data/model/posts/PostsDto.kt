package com.example.homework_22.data.model.posts

import com.squareup.moshi.Json

data class PostsDto(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    @Json(name = "share_content")
    val shareContent: String,
    val owner: OwnerDto
)