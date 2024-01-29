package com.example.fake_posts_task.present.view.user_data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fake_posts_task.domain.usecase.UserItemUseCase
import com.example.fake_posts_task.present.mapper.toUserUiModel
import com.example.fake_posts_task.present.view.user_data.view.UserItemState
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
class UserItemViewModel @Inject constructor(val useCase: UserItemUseCase) : ViewModel() {
    private val _UserItemState: MutableStateFlow<UserItemState.Display> =
        MutableStateFlow(UserItemState.Display())
    val userItemState = _UserItemState.asStateFlow()
    private val _errorState: MutableSharedFlow<UserItemState.Failure> = MutableSharedFlow()
    val errorState = _errorState.asSharedFlow()

    fun getDataByUserId(id: String) {
        _UserItemState.update {
            it.copy(loading = true)
        }

        viewModelScope.launch {
            useCase.getDataByUserId(id).collectLatest { response ->
                when (response) {
                    is Response.Success -> {
                        response.data?.let {
                            _UserItemState.update { state ->
                                state.copy(
                                    userUiModel = response.data.toUserUiModel(),loading = false
                                )
                            }
                        }

                    }

                    is Response.Failure -> {
                        response.error?.let { errorMessage ->
                            _errorState.emit(UserItemState.Failure(errorMessage))
                        }
                        _UserItemState.update { it.copy(loading = false) }
                    }
                }
            }
        }
    }
}