package com.example.homework_22.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.usecase.posts.GetPostsDetailsUseCase
import com.example.homework_22.presentation.event.details.DetailsEvent
import com.example.homework_22.presentation.mapper.main.posts.toPresentation
import com.example.homework_22.presentation.state.details.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostsDetailsUseCase: GetPostsDetailsUseCase
) : ViewModel() {

    private val _detailsState = MutableStateFlow(DetailsState())
    val detailsState: SharedFlow<DetailsState> get() = _detailsState

    private val _navigationEvent = MutableSharedFlow<DetailNavigationEvents>()
    val navigationEvent: SharedFlow<DetailNavigationEvents> get() = _navigationEvent

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetPostsDetails -> getPostsDetails(event.postId)
            is DetailsEvent.BackButtonPressed -> onBackButtonPressed()
        }
    }

    private fun getPostsDetails(postId: Int) {
        viewModelScope.launch {
            handleResource(getPostsDetailsUseCase.invoke(postId)) { data ->
                _detailsState.update { currentState ->
                    currentState.copy(
                        details = data.toPresentation()
                    )
                }
            }
        }
    }

    private fun updateErrorMessages(errorMessage: String) {
        _detailsState.update { currentState ->
            currentState.copy(
                errorMessage = errorMessage
            )
        }
    }

    private fun <T> handleResource(resourceFlow: Flow<Resource<T>>, handleSuccess: (T) -> Unit) {
        viewModelScope.launch {
            resourceFlow.collect { resource ->
                when (resource) {
                    is Resource.Success -> handleSuccess(resource.data)
                    is Resource.Error -> updateErrorMessages(resource.errorMessage)
                    is Resource.Loading -> _detailsState.update { currentState ->
                        currentState.copy(isLoading = resource.loading)
                    }
                }
            }
        }
    }

    private fun onBackButtonPressed() {
        viewModelScope.launch {
            _navigationEvent.emit(DetailNavigationEvents.NavigateToMainFragment)
        }
    }
    sealed interface DetailNavigationEvents {
        data object NavigateToMainFragment : DetailNavigationEvents
    }
}