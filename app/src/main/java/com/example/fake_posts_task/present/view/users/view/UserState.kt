package com.example.fake_posts_task.present.view.users.view

import com.example.fake_posts_task.present.model.user.UserUiModel

sealed class UserState {
    data class Display(
        var userUiModel : List<UserUiModel> = listOf( UserUiModel(
            body = "", id = 1, title = "", userId = 1)),
        val loading: Boolean = false
    ) : UserState()

    data class Failure(val errorMsg: String = "") : UserState()
}