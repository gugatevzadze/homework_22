package com.example.homework_22.presentation.event.main

import com.example.homework_22.presentation.model.main.posts.PostsModel

sealed class MainEvents {
    data object GetPostsList : MainEvents()
    data object GetStoriesList : MainEvents()
    data class PostsItemClick(val post: PostsModel) : MainEvents()
}