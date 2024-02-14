package com.example.homework_22.presentation.state.main

import com.example.homework_22.presentation.model.main.posts.PostsModel
import com.example.homework_22.presentation.model.main.stories.StoriesModel

data class MainState(
    val posts: List<PostsModel>? = null,
    val stories: List<StoriesModel>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)