package com.example.fake_posts_task.present.view.users.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_posts_task.domain.usecase.UserUseCase
import com.example.fake_posts_task.present.mapper.toUserUiModel
import com.example.fake_posts_task.present.view.users.view.UserState
import com.example.fake_posts_task.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val useCase: UserUseCase) : ViewModel() {
    private val _UserState: MutableStateFlow<UserState.Display> =
        MutableStateFlow(UserState.Display())
    val profileState = _UserState.asStateFlow()
    private val _errorState: MutableSharedFlow<UserState.Failure> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getUser() {
        _UserState.update {
            it.copy(loading = true)
        }
        viewModelScope.launch {

            useCase.getUser().collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _UserState.update { state ->
                                state.copy(
                                    userUiModel = response.data.map {
                                        it.toUserUiModel()
                                    }
                                )
                            }
                        }
                    }

                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(UserState.Failure(errorMessage))
                        }
                        _UserState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}