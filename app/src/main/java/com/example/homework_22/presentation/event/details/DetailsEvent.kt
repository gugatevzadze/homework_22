package com.example.homework_22.presentation.event.details

sealed class DetailsEvent {
    data class GetPostsDetails(val postId: Int) : DetailsEvent()
    data object BackButtonPressed : DetailsEvent()
}