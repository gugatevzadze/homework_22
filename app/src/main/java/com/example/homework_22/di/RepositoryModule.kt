package com.example.homework_22.di

import com.example.homework_22.data.common.ResponseHandler
import com.example.homework_22.data.repository.posts.PostsRepositoryImpl
import com.example.homework_22.data.repository.stories.StoriesRepositoryImpl
import com.example.homework_22.data.service.posts.PostsApiService
import com.example.homework_22.data.service.stories.StoriesApiService
import com.example.homework_22.domain.repository.posts.PostsRepository
import com.example.homework_22.domain.repository.stories.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStoriesRepository(
        apiService: StoriesApiService,
        responseHandler: ResponseHandler
    ): StoriesRepository {
        return StoriesRepositoryImpl(
            apiService = apiService,
            responseHandler = responseHandler
        )
    }

    @Provides
    @Singleton
    fun providePostsRepository(
        apiService: PostsApiService,
        responseHandler: ResponseHandler
    ): PostsRepository {
        return PostsRepositoryImpl(
            apiService = apiService,
            responseHandler = responseHandler
        )
    }
}