package com.example.homework_22.presentation.screen.details

import android.util.Log.d
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.homework_22.databinding.FragmentDetailsBinding
import com.example.homework_22.presentation.adapters.main.custom_layout.CustomLayoutManager
import com.example.homework_22.presentation.adapters.main.posts.ImagesRecyclerAdapter
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.details.DetailsEvent
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.extension.showSnackBar
import com.example.homework_22.presentation.state.details.DetailsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var imagesAdapter: ImagesRecyclerAdapter

    override fun setUp() {
        extractPostId()
        adapterSetUp()
        handleBackButton()
    }

    override fun bindObservers() {
        observePostDetails()
        observeNavigation()
    }

    private fun adapterSetUp(){
        imagesAdapter = ImagesRecyclerAdapter()
        binding.rvImages.apply {
            adapter = imagesAdapter
            layoutManager = CustomLayoutManager()
        }
    }

    private fun observePostDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailsState.collect {
                    handlePostDetails(it)
                }
            }
        }
    }

    private fun observeNavigation() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigationEvent.collect {
                    handleNavigation(it)

                }
            }
        }
    }

    private fun handlePostDetails(state: DetailsState) {
        state.details?.let {
            binding.apply {
                tvUser.text = it.owner.fullName
                ivProfile.loadImage(it.owner.profile)
                tvDate.text = it.owner.postDate
                tvTitle.text = it.title
                "${it.comments} Comments".also { tvMessages.text = it }
                "${it.likes} Likes".also { tvLikes.text = it }
                imagesAdapter.submitList(it.images)
                val params = rvImages.layoutParams
                if (it.images.isNullOrEmpty()) {
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                }
                rvImages.layoutParams = params
            }
        }
        state.errorMessage?.let {
            binding.root.showSnackBar(it)
        }
        binding.progressBar.isVisible = state.isLoading
    }

    private fun handleNavigation(event: DetailsViewModel.DetailNavigationEvents) {
        when (event) {
            is DetailsViewModel.DetailNavigationEvents.NavigateToMainFragment -> { findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToMainFragment())
            }
        }
    }

    private fun handleBackButton() {
        binding.ibBack.setOnClickListener {
            viewModel.onEvent(DetailsEvent.BackButtonPressed)
        }
    }


    private fun extractPostId() {
        val postId = arguments?.getInt("postId") ?: -1
        d("details","postId: $postId")
        viewModel.onEvent(DetailsEvent.GetPostsDetails(postId = postId))
    }
}
