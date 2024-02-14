package com.example.homework_22.data.repository.posts

import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.common.ResponseHandler
import com.example.homework_22.data.mapper.base.mapToDomain
import com.example.homework_22.data.mapper.posts.toDomain
import com.example.homework_22.data.service.posts.PostsApiService
import com.example.homework_22.domain.model.stories.Posts
import com.example.homework_22.domain.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val apiService: PostsApiService,
    private val responseHandler: ResponseHandler
) : PostsRepository {
    override suspend fun getPostsList(): Flow<Resource<List<Posts>>> {
        return responseHandler.safeApiCall {
            apiService.getPostsList()
        }.mapToDomain { postsDto ->
            postsDto.map { it.toDomain() }
        }
    }
}