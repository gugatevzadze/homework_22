package com.example.homework_22.presentation.mapper.main.stories

import com.example.homework_22.domain.model.stories.Stories
import com.example.homework_22.presentation.model.main.stories.StoriesModel

fun Stories.toPresentation(): StoriesModel {
    return StoriesModel(
        id = id,
        cover = cover,
        title = title
    )
}