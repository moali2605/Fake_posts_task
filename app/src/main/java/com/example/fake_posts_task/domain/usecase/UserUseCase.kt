package com.example.fake_posts_task.domain.usecase

import com.example.fake_posts_task.domain.model.user.UserDomainModel
import com.example.fake_posts_task.domain.repo.RepositoryInterface
import com.example.fake_posts_task.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: RepositoryInterface) {
    suspend fun getUser(): Flow<Response<List<UserDomainModel>>> {
        return repository.getUsers()
    }
}