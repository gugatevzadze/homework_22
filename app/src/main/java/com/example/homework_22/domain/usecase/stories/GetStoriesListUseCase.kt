package com.example.homework_22.domain.usecase.stories

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.stories.Stories
import com.example.homework_22.domain.repository.stories.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoriesListUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Stories>>> {
        return storiesRepository.getStoriesList()
    }
}