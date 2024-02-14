package com.example.homework_22.domain.usecase.stories

import com.example.homework_22.domain.repository.posts.PostsRepository
import javax.inject.Inject

class GetPostsListUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke() = postsRepository.getPostsList()
}