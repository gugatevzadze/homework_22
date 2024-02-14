package com.example.homework_22.presentation.screen.main

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.homework_22.databinding.FragmentMainBinding
import com.example.homework_22.presentation.adapters.main.posts.PostsRecyclerAdapter
import com.example.homework_22.presentation.adapters.main.stories.StoriesRecyclerAdapter
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.main.MainEvents
import com.example.homework_22.presentation.extension.showSnackBar
import com.example.homework_22.presentation.state.main.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var storiesAdapter: StoriesRecyclerAdapter
    private lateinit var postsAdapter: PostsRecyclerAdapter

    override fun setUp() {
        storiesAdapterSetUp()
        postsAdapterSetUp()
    }

    override fun bindObservers() {
        observeMainState()
    }

    private fun storiesAdapterSetUp() {
        storiesAdapter = StoriesRecyclerAdapter()
        binding.rvStories.apply {
            adapter = storiesAdapter
            layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
        }
        viewModel.onEvent(MainEvents.GetStoriesList)
    }

    private fun postsAdapterSetUp() {
        postsAdapter = PostsRecyclerAdapter()
        binding.rvPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        }
        viewModel.onEvent(MainEvents.GetPostsList)
    }

    private fun observeMainState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    handleMainState(it)
                }
            }
        }
    }

    private fun handleMainState(state: MainState) {
        state.posts?.let {
            postsAdapter.submitList(it)
        }
        state.stories?.let {
            storiesAdapter.submitList(it)
        }
        state.errorMessage?.let {
            binding.root.showSnackBar(it)
        }
        binding.progressBar.isVisible = state.isLoading
    }
}