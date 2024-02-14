package com.example.homework_22.data.mapper.stories

import com.example.homework_22.data.model.stories.StoriesDto
import com.example.homework_22.domain.model.posts.Stories

fun StoriesDto.toDomain(): Stories {
    return Stories(
        id = id,
        cover = cover,
        title = title
    )
}