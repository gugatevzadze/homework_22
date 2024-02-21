package com.example.homework_22.presentation.screen.main

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.posts.Owner
import com.example.homework_22.domain.model.posts.Posts
import com.example.homework_22.domain.model.stories.Stories
import com.example.homework_22.domain.usecase.posts.GetPostsListUseCase
import com.example.homework_22.domain.usecase.stories.GetStoriesListUseCase
import com.example.homework_22.presentation.event.main.MainEvents
import com.example.homework_22.presentation.mapper.main.posts.toPresentation
import com.example.homework_22.presentation.mapper.main.stories.toPresentation
import com.example.homework_22.presentation.model.main.posts.OwnerModel
import com.example.homework_22.presentation.model.main.posts.PostsModel
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
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var getPostsListUseCase: GetPostsListUseCase

    @Mock
    private lateinit var getStoriesListUseCase: GetStoriesListUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)

        viewModel = MainViewModel(getPostsListUseCase, getStoriesListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        Mockito.verifyNoMoreInteractions(getPostsListUseCase, getStoriesListUseCase)
    }

    @Test
    fun `onEvent PostsItemClick navigates to the correct fragment with correct postid`() = runTest {
        val post = PostsModel(
            id = 5402,
            images = listOf(),
            title = "meliore",
            comments = 1781,
            likes = 3619,
            shareContent = "ceteros",
            owner = OwnerModel(
                fullName = "Randi Harmon",
                profile = null,
                postDate = "verear"
            )
        )
        viewModel.onEvent(MainEvents.PostsItemClick(post))
        val result = viewModel.mainNavigationEvent.first()
        Assertions.assertEquals(
            MainViewModel.MainNavigationEvents.NavigateToPostDetails(postId = post.id),
            result
        )
    }

    @Test
    fun `getPostsList updates mainState with correct success`() = runTest {

        val posts = listOf(
            Posts(
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
            ),
            Posts(
                id = 4350,
                images = listOf(),
                title = "malorum",
                comments = 7837,
                likes = 2280,
                shareContent = "mauris",
                owner = Owner(
                    firstName = "Anton Harmon",
                    lastName = null,
                    profile = null,
                    postDate = 3714
                )
            )
        )
        whenever(getPostsListUseCase()).thenReturn(flowOf(Resource.Success(posts)))

        viewModel.onEvent(MainEvents.GetPostsList)

        val result = viewModel.mainState.take(2).toList()

        assertTrue(
            result.any { it -> it.posts == posts.map { it.toPresentation() }}
        )
        assertFalse(
            result.last().isLoading
        )

        Mockito.verify(getPostsListUseCase).invoke()
    }

    @Test
    fun `getPostsList updates mainState with error message when GetPostsListUseCase returns error`() = runTest {
        val errorMessage = "Error message"
        whenever(getPostsListUseCase()).thenReturn(flowOf(Resource.Error(errorMessage)))

        viewModel.onEvent(MainEvents.GetPostsList)

        val result = viewModel.mainState.take(2).toList()

        assertTrue(
            result.any { it.errorMessage == errorMessage }
        )

        Mockito.verify(getPostsListUseCase).invoke()
    }

    @Test
    fun `getPostsList updates mainState with loading when GetPostsListUseCase returns loading`() = runTest {
        whenever(getPostsListUseCase()).thenReturn(flowOf(Resource.Loading(true)))

        viewModel.onEvent(MainEvents.GetPostsList)

        val result = viewModel.mainState.take(2).toList()

        assertTrue(
            result.any { it.isLoading }
        )

        Mockito.verify(getPostsListUseCase).invoke()
    }

    ///stories
    @Test
    fun `getStoriesList updates mainState with stories when GetStoriesListUseCase returns success`() = runTest {
        val stories = listOf(
            Stories(id = "facilisi", cover = "atqui", title = "adolescens"),
            Stories(id = "dolore", cover = "dolore", title = "dolore"),
            Stories(id = "electram", cover = "molestie", title = "sed"),
        )
        whenever(getStoriesListUseCase()).thenReturn(flowOf(Resource.Success(stories)))

        viewModel.onEvent(MainEvents.GetStoriesList)

        val result = viewModel.mainState.take(2).toList()

        assertTrue(
            result.any { it -> it.stories == stories.map { it.toPresentation() }}
        )
        assertFalse(
            result.last().isLoading
        )

        Mockito.verify(getStoriesListUseCase).invoke()
    }

    @Test
    fun `getStoriesList updates mainState with error message when GetStoriesListUseCase returns error`() = runTest {
        val expectedErrorMessage = "Error message"
        whenever(getStoriesListUseCase()).thenReturn(flowOf(Resource.Error(expectedErrorMessage)))

        viewModel.onEvent(MainEvents.GetStoriesList)

        val result= viewModel.mainState.take(2).toList()

        assertTrue(
            result.any { it.errorMessage == expectedErrorMessage }
        )
        assertFalse(
            result.last().isLoading
        )

        Mockito.verify(getStoriesListUseCase).invoke()
    }

    @Test
    fun `getStoriesList updates mainState with loading when GetStoriesListUseCase returns loading`() = runTest {
        whenever(getStoriesListUseCase()).thenReturn(flowOf(Resource.Loading(true)))

        viewModel.onEvent(MainEvents.GetStoriesList)

        val result = viewModel.mainState.take(2).toList()

        assertTrue(
            result.any { it.isLoading }
        )

        Mockito.verify(getStoriesListUseCase).invoke()
    }
}
