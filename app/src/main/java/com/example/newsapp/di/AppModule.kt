package com.example.newsapp.di

import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.api.NewsApi.Companion.BASE_URL
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.INewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * This method provides the NewsApi instance
     * @return NewsApi
     */
    @Provides
    @Singleton
    fun provideNewsApi() :NewsApi{
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi) : INewsRepository {
        return NewsRepositoryImpl(newsApi)
    }
}