package com.example.newsapp.data.api
import com.example.newsapp.domain.model.NewsResponse
import com.example.newsapp.utils.getDate
import com.example.newsapp.utils.getPreviousDate
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object {
        const val BASE_URL = "https://www.newsapi.ai/api/v1/article/"
        const val API_KEY = "0d077d3b-0d32-49ea-968a-f3fe1e73405d"
        val currentDate = getDate()
        val previousWeekDate = getPreviousDate(7)
    }

    @GET("getArticles")
    suspend fun getTopNews(
        @Query("dateStart") dateStart: String = previousWeekDate,
        @Query("dateEnd") dateEnd: String = currentDate,
        @Query("lang") lang: String = "ces",
        @Query("resultType") resultType: String = "articles",
        @Query("articlesSortBy") articlesSortBy: String = "rel",
        @Query("includeArticleImage") includeArticleImage: Boolean = true,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}
