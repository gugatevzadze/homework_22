package com.example.homework_22.presentation.event.main

sealed class MainEvents {
    data object GetPostsList : MainEvents()
    data object GetStoriesList : MainEvents()
}