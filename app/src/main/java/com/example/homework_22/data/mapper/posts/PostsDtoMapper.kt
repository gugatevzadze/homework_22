package com.example.homework_22.data.mapper.posts

import com.example.homework_22.data.model.posts.OwnerDto
import com.example.homework_22.data.model.posts.PostsDto
import com.example.homework_22.domain.model.stories.Owner
import com.example.homework_22.domain.model.stories.Posts

fun PostsDto.toDomain(): Posts {
    return Posts(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}

fun OwnerDto.toDomain(): Owner {
    return Owner(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate
    )
}