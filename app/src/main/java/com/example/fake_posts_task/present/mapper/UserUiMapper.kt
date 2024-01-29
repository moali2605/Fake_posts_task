package com.example.fake_posts_task.present.mapper

import com.example.fake_posts_task.domain.model.user.UserDomainModel
import com.example.fake_posts_task.present.model.user.UserUiModel

fun UserDomainModel.toUserUiModel(): UserUiModel =
    UserUiModel(
        body = body, id = id, title = title, userId = userId
    )