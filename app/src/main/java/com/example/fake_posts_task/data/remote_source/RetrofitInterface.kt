package com.example.fake_posts_task.data.remote_source

import com.example.fake_posts_task.data.model.user.UserDto
import com.example.fake_posts_task.data.model.user.UserDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    companion object {
        const val USERS_ENDPOINT = "/posts"
    }

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): UserDto

    @GET("$USERS_ENDPOINT/{id}")
    suspend fun getDataByUserId(@Path("id") id: String): UserDtoItem
}