package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.database.ArticleDao
import com.example.newsapp.data.database.ArticleEntity
import com.example.newsapp.domain.model.articleDetailResponse.ArticleDetail
import com.example.newsapp.domain.model.articleDetailResponse.Info
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.domain.repository.INewsRepository
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(private val newsApi: NewsApi, private val articleDao: ArticleDao) : INewsRepository {

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

    override suspend fun getAllArticlesFromDb(): Flow<List<ArticleEntity>> {
        return articleDao.getAllArticlesFromDb()
    }

    override suspend fun insertArticleToDb(article: Result) {
        val articleToSave: ArticleEntity = ArticleEntity(
            authors = article.authors.firstOrNull()?.name,
            body = article.body,
            dataType = article.dataType,
            date = article.date,
            dateTime = article.dateTime,
            dateTimePub = article.dateTimePub,
            eventUri = article.eventUri,
            image = article.image,
            isDuplicate = article.isDuplicate,
            lang = article.lang,
            relevance = article.relevance,
            sentiment = article.sentiment,
            sim = article.sim,
            source = article.source?.title,
            time = article.time,
            title = article.title,
            uri = article.uri,
            url = article.url,
            wgt = article.wgt
        )
        Log.d("ArticleToSave", "saveArticle: $articleToSave")
        withContext(Dispatchers.IO){
            articleDao.insertArticleToDb(articleToSave)
        }
    }

    override suspend fun deleteArticleFromDb(article: ArticleEntity) {
        articleDao.deleteArticleFromDb(article)
    }
}


