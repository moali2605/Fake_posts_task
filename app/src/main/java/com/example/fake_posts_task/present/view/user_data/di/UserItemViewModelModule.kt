package com.example.fake_posts_task.present.view.user_data.di

import com.example.fake_posts_task.data.repo.Repository
import com.example.fake_posts_task.domain.usecase.UserItemUseCase
import com.example.fake_posts_task.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserItemViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideUserItemUseCase(repository: Repository): UserItemUseCase {
        return UserItemUseCase(repository)
    }
}