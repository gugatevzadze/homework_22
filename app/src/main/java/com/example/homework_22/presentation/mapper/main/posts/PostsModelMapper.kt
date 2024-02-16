package com.example.homework_22.presentation.mapper.main.posts

import com.example.homework_22.domain.model.posts.Owner
import com.example.homework_22.domain.model.posts.Posts
import com.example.homework_22.presentation.model.main.posts.OwnerModel
import com.example.homework_22.presentation.model.main.posts.PostsModel
import com.example.homework_22.presentation.util.DateUtil

fun Posts.toPresentation(): PostsModel {
    return PostsModel(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toPresentation()
    )
}
fun Owner.toPresentation(): OwnerModel {
    return OwnerModel(
        fullName = firstName + " " + (lastName ?: ""),
        profile = profile,
        postDate = DateUtil.epochToReadableDate(postDate)
    )
}