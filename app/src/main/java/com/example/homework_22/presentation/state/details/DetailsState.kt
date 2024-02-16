package com.example.homework_22.presentation.state.details

import com.example.homework_22.presentation.model.main.posts.PostsModel
import com.example.homework_22.presentation.model.main.stories.StoriesModel

data class DetailsState(
    val details: PostsModel? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)