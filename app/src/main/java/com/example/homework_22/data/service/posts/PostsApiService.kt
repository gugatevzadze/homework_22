package com.example.homework_22.data.service.posts

import com.example.homework_22.data.model.posts.PostsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApiService {
    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPostsList(): Response<List<PostsDto>>

    @GET("d02b76bb-095d-45fa-90e1-dc4733d1f247")
    suspend fun getPostsDetails(@Query("id") id: Int): Response<PostsDto>
}