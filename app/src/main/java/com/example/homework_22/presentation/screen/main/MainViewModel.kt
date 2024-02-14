package com.example.homework_22.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.usecase.posts.GetStoriesListUseCase
import com.example.homework_22.domain.usecase.stories.GetPostsListUseCase
import com.example.homework_22.presentation.event.main.MainEvents
import com.example.homework_22.presentation.mapper.main.posts.toPresentation
import com.example.homework_22.presentation.mapper.main.stories.toPresentation
import com.example.homework_22.presentation.state.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostsListUseCase: GetPostsListUseCase,
    private val getStoriesListUseCase: GetStoriesListUseCase
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState:SharedFlow<MainState> get() = _mainState

    fun onEvent(event: MainEvents) {
        when (event) {
            is MainEvents.GetPostsList -> getPostsList()
            is MainEvents.GetStoriesList -> getStoriesList()
        }
    }

    private fun getPostsList(){
        viewModelScope.launch {
            handleResource(getPostsListUseCase.invoke()) { data ->
                _mainState.update { currentState ->
                    currentState.copy(posts = data.map { it.toPresentation() })
                }
            }
        }
    }

    private fun getStoriesList(){
        viewModelScope.launch {
            handleResource(getStoriesListUseCase.invoke()) { data ->
                _mainState.update { currentState ->
                    currentState.copy(stories = data.map { it.toPresentation() })
                }
            }
        }
    }

    private fun updateErrorMessages(message: String?) {
        _mainState.update { currentState ->
            currentState.copy(
                errorMessage = message
            )
        }
    }

    private fun <T> handleResource(resourceFlow: Flow<Resource<T>>, handleSuccess: (T) -> Unit) {
        viewModelScope.launch {
            resourceFlow.collect { resource ->
                when (resource) {
                    is Resource.Success -> handleSuccess(resource.data)
                    is Resource.Error -> updateErrorMessages(resource.errorMessage)
                    is Resource.Loading -> _mainState.update { currentState ->
                        currentState.copy(isLoading = resource.loading)
                    }
                }
            }
        }
    }
}