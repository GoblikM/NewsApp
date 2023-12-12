package com.example.newsapp.data.api

import com.example.newsapp.domain.model.articleDetailResponse.ArticleDetail
import com.example.newsapp.domain.model.newsResponse.NewsResponse
import com.example.newsapp.utils.getDate
import com.example.newsapp.utils.getPreviousDate
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
     * This companion object will hold the base url and api key
     * @property BASE_URL
     * @property API_KEY
     * @property currentDate
     * @property previousWeekDate
     */
    companion object {
        const val BASE_URL = "https://www.newsapi.ai/api/v1/article/"
        const val API_KEY = "0d077d3b-0d32-49ea-968a-f3fe1e73405d"
        val currentDate = getDate()
        val previousWeekDate = getPreviousDate(7)
    }

    /**
     * This function will fetch the news from the API
     * @return NewsResponse
     */
    @GET("getArticles")
    suspend fun getTopNews(
        @Query("dateStart") dateStart: String = previousWeekDate,
        @Query("dateEnd") dateEnd: String = currentDate,
        @Query("lang") lang: String = "ces",
        @Query("isDuplicate") isDuplicate : String = "skipDuplicates",
        @Query("resultType") resultType: String = "articles",
        @Query("articlesSortBy") articlesSortBy: String = "rel",
        @Query("includeArticleImage") includeArticleImage: Boolean = true,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    /**
     * This function will fetch the article details from the API
     * @param articleUri
     * @return Map<String, ArticleDetail>
     */
    @GET("getArticle")
    suspend fun getArticle(
        @Query("articleUri") articleUri: String,
        @Query("resultType") resultType: String = "info",
        @Query("includeArticleImage") includeArticleImage: Boolean = true,
        @Query("includeConceptLabel") includeConceptLabel: Boolean = false,
        @Query("apiKey") apiKey: String = API_KEY,
    ) : Map<String, ArticleDetail>
}
