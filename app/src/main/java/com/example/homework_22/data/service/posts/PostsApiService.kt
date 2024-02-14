package com.example.homework_22.data.service.posts

import com.example.homework_22.data.model.posts.PostsDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {
    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPostsList(): Response<List<PostsDto>>
}