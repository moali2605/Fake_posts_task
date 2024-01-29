package com.example.fake_posts_task.domain.repo

import com.example.fake_posts_task.data.model.user.UserDtoItem
import com.example.fake_posts_task.domain.model.user.UserDomainModel
import com.example.fake_posts_task.util.Response
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun getUsers(): Flow<Response<List<UserDomainModel>>>
    suspend fun getDataByUserId(id:String):Flow<Response<UserDomainModel>>
}