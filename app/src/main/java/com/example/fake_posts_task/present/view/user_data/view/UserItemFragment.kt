package com.example.fake_posts_task.present.view.user_data.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fake_posts_task.R
import com.example.fake_posts_task.databinding.FragmentUserItemBinding
import com.example.fake_posts_task.present.view.user_data.viewModel.UserItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserItemFragment : Fragment() {
    private lateinit var binding: FragmentUserItemBinding
    private val userItemViewModel by viewModels<UserItemViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = UserItemFragmentArgs.fromBundle(requireArguments()).id
        userItemViewModel.getDataByUserId(id)
        initObservers()

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {

            userItemViewModel.userItemState.collectLatest { userItemState ->
                if (userItemState.loading) {
                    showLoadingState()
                } else {
                    populateUi(userItemState)
                }
            }
            userItemViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            loading.visibility = View.VISIBLE
            tvItemUserId.visibility = View.GONE
            tvItemTitle.visibility = View.GONE
            tvItemBody.visibility = View.GONE
            loading.setAnimation(R.raw.loading)
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(userItemState: UserItemState.Display) {
        binding.apply {
            loading.visibility = View.GONE
            tvItemUserId.visibility = View.VISIBLE
            tvItemTitle.visibility = View.VISIBLE
            tvItemBody.visibility = View.VISIBLE
            tvItemUserId.text = userItemState.userUiModel.userId.toString()
            tvItemTitle.text = userItemState.userUiModel.title
            tvItemBody.text = userItemState.userUiModel.body
        }
    }

    private fun showErrorState(errorState: UserItemState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}