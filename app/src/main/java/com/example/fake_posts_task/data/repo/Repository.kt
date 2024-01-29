package com.example.fake_posts_task.data.repo

import com.example.fake_posts_task.data.mapper.toUserDomainModel
import com.example.fake_posts_task.data.mapper.toUserItemDomainModel
import com.example.fake_posts_task.data.model.user.UserDtoItem
import com.example.fake_posts_task.data.remote_source.RemoteSourceInterface
import com.example.fake_posts_task.domain.model.user.UserDomainModel
import com.example.fake_posts_task.domain.repo.RepositoryInterface
import com.example.fake_posts_task.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(private val remoteSourceInterface: RemoteSourceInterface) :
    RepositoryInterface {
    override suspend fun getUsers(): Flow<Response<List<UserDomainModel>>> {
        return try {
            remoteSourceInterface.getUsers().map {
                Response.Success(it.toUserDomainModel())
            }
        } catch (e: Exception) {
            flowOf(Response.Failure(e.message ?: "Unknown Exception"))
        }
    }

    override suspend fun getDataByUserId(id: String): Flow<Response<UserDomainModel>> {
        return try {
            remoteSourceInterface.getDataByUserId(id).map {
                Response.Success(it.toUserItemDomainModel())
            }
        }catch (e:Exception){
            flowOf(Response.Failure(e.message ?: "Unknown Exception"))
        }
    }
}