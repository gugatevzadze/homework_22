package com.example.homework_22.data.repository.stories

import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.common.ResponseHandler
import com.example.homework_22.data.mapper.base.mapToDomain
import com.example.homework_22.data.mapper.stories.toDomain
import com.example.homework_22.data.service.stories.StoriesApiService
import com.example.homework_22.domain.model.posts.Stories
import com.example.homework_22.domain.repository.stories.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val apiService: StoriesApiService,
    private val responseHandler: ResponseHandler
): StoriesRepository {
    override suspend fun getStoriesList(): Flow<Resource<List<Stories>>> {
        return responseHandler.safeApiCall {
            apiService.getStoriesList()
        }.mapToDomain { storiesDto ->
            storiesDto.map { it.toDomain() }
        }
    }
}