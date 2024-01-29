package com.example.fake_posts_task.present.view.user_data.view

import com.example.fake_posts_task.present.model.user.UserUiModel
import com.example.fake_posts_task.present.view.users.view.UserState

sealed class UserItemState {
    data class Display(
        var userUiModel: UserUiModel = UserUiModel(
            body = "", id = 1, title = "", userId = 1
        ),
        val loading: Boolean = false
    ) : UserItemState()

    data class Failure(val errorMsg: String = "") : UserItemState()
}