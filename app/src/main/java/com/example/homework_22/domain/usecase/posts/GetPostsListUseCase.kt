package com.example.homework_22.domain.usecase.posts

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.posts.Posts
import com.example.homework_22.domain.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsListUseCase @Inject constructor(
    private val postsRepository: PostsRepository
){
    suspend operator fun invoke(): Flow<Resource<List<Posts>>>{
        return postsRepository.getPostsList()
    }
}