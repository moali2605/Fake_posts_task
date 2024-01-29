package com.example.fake_posts_task.present.model.user

import java.io.Serializable

data class UserUiModel(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Serializable
