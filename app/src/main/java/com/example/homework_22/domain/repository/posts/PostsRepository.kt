package com.example.homework_22.domain.repository.posts

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.stories.Posts
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPostsList(): Flow<Resource<List<Posts>>>
}