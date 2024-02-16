package com.example.homework_22.domain.usecase.stories

import com.example.homework_22.domain.repository.stories.StoriesRepository
import javax.inject.Inject

class GetStoriesListUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend operator fun invoke() = storiesRepository.getStoriesList()
}