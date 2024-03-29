package com.example.homework_22.domain.model.stories

import com.squareup.moshi.Json

data class Posts(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: Owner
)