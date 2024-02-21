package com.example.homework_22.presentation.screen.details

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.posts.Owner
import com.example.homework_22.domain.model.posts.Posts
import com.example.homework_22.domain.usecase.posts.GetPostsDetailsUseCase
import com.example.homework_22.presentation.event.details.DetailsEvent
import com.example.homework_22.presentation.event.main.MainEvents
import com.example.homework_22.presentation.mapper.main.posts.toPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    private lateinit var viewModel: DetailsViewModel

    @Mock
    private lateinit var getPostsDetailsUseCase: GetPostsDetailsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)

        viewModel = DetailsViewModel(getPostsDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        Mockito.verifyNoMoreInteractions(getPostsDetailsUseCase)
    }

    @Test
    fun `onEvent BackButtonPressed navigates to the correct fragment`() = runTest {
        val event = DetailsEvent.BackButtonPressed
        viewModel.onEvent(event)

        val navigationEvents = viewModel.navigationEvent.first()
        assertTrue(DetailsViewModel.DetailNavigationEvents.NavigateToMainFragment == navigationEvents)
    }

    @Test
    fun `getPostDetails updates detailsState with correct success`() = runTest {

        val post = Posts(
            id = 6017,
            images = listOf(),
            title = "discere",
            comments = 7287,
            likes = 5785,
            shareContent = "invidunt",
            owner = Owner(
                firstName = "Carlo Nichols",
                lastName = null,
                profile = null,
                postDate = 1466
            )
        )
        whenever(getPostsDetailsUseCase(6017)).thenReturn(flowOf(Resource.Success(post)))

        viewModel.onEvent(DetailsEvent.GetPostsDetails(6017))

        val result = viewModel.detailsState.take(2).toList()

        assertTrue(
            result.any { it.details == post.toPresentation() }
        )
        assertFalse(
            result.last().isLoading
        )

        Mockito.verify(getPostsDetailsUseCase).invoke(6017)
    }

    @Test
    fun `getPostDetails updates detailsState with error message when GetPostsDetailsUseCase returns error`() = runTest {
        val errorMessage = "Error message"
        whenever(getPostsDetailsUseCase(6017)).thenReturn(flowOf(Resource.Error(errorMessage)))

        viewModel.onEvent(DetailsEvent.GetPostsDetails(6017))

        val result = viewModel.detailsState.take(2).toList()

        assertTrue(
            result.any { it.errorMessage == errorMessage }
        )

        Mockito.verify(getPostsDetailsUseCase).invoke(6017)
    }

    @Test
    fun `getPostDetails updates detailsState with loading when GetPostsDetailsUseCase returns loading`() = runTest {
        whenever(getPostsDetailsUseCase(6017)).thenReturn(flowOf(Resource.Loading(true)))

        viewModel.onEvent(DetailsEvent.GetPostsDetails(6017))

        val result = viewModel.detailsState.take(2).toList()

        assertTrue(
            result.any { it.isLoading }
        )

        Mockito.verify(getPostsDetailsUseCase).invoke(6017)
    }
}