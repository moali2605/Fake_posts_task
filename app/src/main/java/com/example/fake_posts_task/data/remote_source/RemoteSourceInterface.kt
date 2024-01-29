package com.example.fake_posts_task.data.remote_source

import com.example.fake_posts_task.data.model.user.UserDto
import com.example.fake_posts_task.data.model.user.UserDtoItem
import kotlinx.coroutines.flow.Flow

interface RemoteSourceInterface {
    suspend fun getUsers(): Flow<UserDto>
    suspend fun getDataByUserId(id:String):Flow<UserDtoItem>
}