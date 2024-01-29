package com.example.fake_posts_task.present.view.users.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fake_posts_task.R
import com.example.fake_posts_task.databinding.FragmentUsersBinding
import com.example.fake_posts_task.present.view.users.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding
    private val UserViewModel by viewModels<UserViewModel>()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initViews() {
        userAdapter = UserAdapter()
        binding.rvAlbums.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            UserViewModel.getUser()
            UserViewModel.profileState.collectLatest { userState ->
                if (userState.loading) {
                    showLoadingState()
                } else {
                    populateUi(userState)
                }
            }
            UserViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            loading.visibility = View.VISIBLE
            loading.setAnimation(R.raw.loading)
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(userState: UserState.Display) {
        binding.loading.visibility = View.GONE
        userAdapter.submitList(userState.userUiModel)
    }

    private fun showErrorState(errorState: UserState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}