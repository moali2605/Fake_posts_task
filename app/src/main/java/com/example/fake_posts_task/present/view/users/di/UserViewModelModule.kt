package com.example.fake_posts_task.present.view.users.di

import com.example.fake_posts_task.data.repo.Repository
import com.example.fake_posts_task.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideUserUseCase(repository: Repository): UserUseCase {
        return UserUseCase(repository)
    }
}