package com.example.homework_22.di

import com.example.homework_22.BuildConfig
import com.example.homework_22.data.common.ResponseHandler
import com.example.homework_22.data.service.posts.PostsApiService
import com.example.homework_22.data.service.stories.StoriesApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).client(provideOkHttpClient()).build()
    }

    @Singleton
    @Provides
    fun providePostsService(
        retrofit: Retrofit
    ): PostsApiService {
        return retrofit.create(PostsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideStoriesService(
        retrofit: Retrofit
    ): StoriesApiService {
        return retrofit.create(StoriesApiService::class.java)
    }
}