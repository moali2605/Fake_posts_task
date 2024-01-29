package com.example.fake_posts_task.data.remote_source

import com.example.fake_posts_task.data.model.user.UserDto
import com.example.fake_posts_task.data.model.user.UserDtoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteSourceImp @Inject constructor(private val retrofitInterface: RetrofitInterface) :RemoteSourceInterface  {
    override suspend fun getUsers(): Flow<UserDto> {
        return flowOf( retrofitInterface.getUsers())
    }

    override suspend fun getDataByUserId(id: String): Flow<UserDtoItem> {
        return flowOf(retrofitInterface.getDataByUserId(id))
    }

}