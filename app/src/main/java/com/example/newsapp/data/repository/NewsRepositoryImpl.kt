package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.domain.model.articleDetailResponse.ArticleDetail
import com.example.newsapp.domain.model.articleDetailResponse.Info
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.domain.repository.INewsRepository
import com.example.newsapp.utils.Resource

class NewsRepositoryImpl(private val newsApi: NewsApi) : INewsRepository {

    /**
     * This function will fetch the news from the API
     * @return Resource<List<Result>>
     *     Success -> List<Result>
     *     Error -> Error message
     *
     */
    override suspend fun getNews(): Resource<List<Result>> {
        return try {
            val response = newsApi.getTopNews()
            Resource.Success(response.articles.results)
        }
        catch (e: Exception) {
            Resource.Error(message = "Failed to fetch the news ${e.message}")
        }
    }

    /**
     * This function will fetch the article details from the API
     * @param articleUri
     * @return Resource<Info>
     *     Success -> Info
     *     Error -> Error message
     *
     */
    override suspend fun getArticleDetails(articleUri: String): Resource<Info> {
        return try {
            val response: Map<String, ArticleDetail> = newsApi.getArticle(articleUri = articleUri)
            Log.d("response", "getArticleDetails: $response" )
            val articleInfo = response[articleUri]?.info
            Resource.Success(articleInfo)

        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch the news ${e.message}")
        }

    }
}


