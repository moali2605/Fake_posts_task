package com.example.fake_posts_task.di

import com.example.fake_posts_task.data.remote_source.RemoteSourceImp
import com.example.fake_posts_task.data.remote_source.RemoteSourceInterface
import com.example.fake_posts_task.data.remote_source.RetrofitInterface
import com.example.fake_posts_task.data.repo.Repository
import com.example.fake_posts_task.domain.repo.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun providesLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            return logging
        }

        @Provides
        @Singleton
        fun providesOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(okHttpLoggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        fun provideUrl(): String = "https://jsonplaceholder.typicode.com"

        @Provides
        @Singleton
        fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): RetrofitInterface {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(baseUrl)
                .build().create(RetrofitInterface::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun ProvideRemoteSource(remoteSourceImp: RemoteSourceImp): RemoteSourceInterface

    @Binds
    @Singleton
    abstract fun provideRepository(repository: Repository): RepositoryInterface

}