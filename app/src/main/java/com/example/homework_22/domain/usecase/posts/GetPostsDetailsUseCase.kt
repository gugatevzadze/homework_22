package com.example.homework_22.domain.usecase.posts

import com.example.homework_22.domain.repository.posts.PostsRepository
import javax.inject.Inject

class GetPostsDetailsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(postId: Int) = postsRepository.getPostsDetails(postId)
}