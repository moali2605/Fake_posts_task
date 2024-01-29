package com.example.fake_posts_task.data.mapper

import com.example.fake_posts_task.data.model.user.UserDto
import com.example.fake_posts_task.data.model.user.UserDtoItem
import com.example.fake_posts_task.domain.model.user.UserDomainModel

fun UserDtoItem.toUserItemDomainModel(): UserDomainModel =
    UserDomainModel(
        body = body, id = id, title = title, userId = userId
    )

fun UserDto.toUserDomainModel(): List<UserDomainModel> = this.map { it.toUserItemDomainModel()}
