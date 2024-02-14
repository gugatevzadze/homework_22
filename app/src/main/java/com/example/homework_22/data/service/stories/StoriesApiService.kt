package com.example.homework_22.data.service.stories

import com.example.homework_22.data.model.stories.StoriesDto
import retrofit2.Response
import retrofit2.http.GET

interface StoriesApiService {
    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStoriesList(): Response<List<StoriesDto>>
}