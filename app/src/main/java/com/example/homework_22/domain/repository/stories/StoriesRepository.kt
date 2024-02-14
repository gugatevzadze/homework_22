package com.example.homework_22.domain.repository.stories

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.posts.Stories
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    suspend fun getStoriesList(): Flow<Resource<List<Stories>>>
}