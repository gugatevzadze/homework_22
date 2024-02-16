package com.example.homework_22.domain.model.posts

data class Owner(
    val firstName: String,
    val lastName: String?,
    val profile: String?,
    val postDate: Long
)