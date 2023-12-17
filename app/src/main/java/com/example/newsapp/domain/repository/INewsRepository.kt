package com.example.newsapp.domain.repository

import com.example.newsapp.data.database.ArticleEntity
import com.example.newsapp.domain.model.articleDetailResponse.Info
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    suspend fun getNews(page: Int):Resource<List<Result>>
    suspend fun getArticleDetails(articleUri:String) :Resource<Info>
    suspend fun getAllArticlesFromDb(): Flow<List<ArticleEntity>>
    suspend fun insertArticleToDb(article: Result)
    suspend fun deleteArticleFromDb(article: ArticleEntity)

}