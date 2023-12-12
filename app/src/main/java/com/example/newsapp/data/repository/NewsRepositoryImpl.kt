package com.example.newsapp.data.repository

import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.domain.repository.INewsRepository
import com.example.newsapp.utils.Resource

class NewsRepositoryImpl(private val newsApi: NewsApi) : INewsRepository {
    override suspend fun getNews(): Resource<List<Result>> {
        return try {
            val response = newsApi.getTopNews()
            Resource.Success(response.articles.results)
        }
        catch (e: Exception) {
            Resource.Error(message = "Failed to fetch the news ${e.message}")
        }
    }
}