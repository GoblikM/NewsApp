package com.example.newsapp.data.api

import com.example.newsapp.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    //https://newsapi.ai/api/v1/article/getArticles?query=%7B%22%24query%22%3A%7B%22%24and%22%3A%5B%7B%22locationUri%22%3A%22http%3A%2F%2Fen.wikipedia.org%2Fwiki%2FCzech_Republic%22%7D%2C%7B%22dateStart%22%3A%222023-12-03%22%2C%22dateEnd%22%3A%222023-12-10%22%2C%22lang%22%3A%22ces%22%7D%5D%7D%2C%22%24filter%22%3A%7B%22isDuplicate%22%3A%22skipDuplicates%22%7D%7D&resultType=articles&articlesSortBy=date&includeArticleCategories=true&includeArticleImage=true&includeSourceTitle=false&includeSourceLocation=true&apiKey=0d077d3b-0d32-49ea-968a-f3fe1e73405d&
    companion object{
        const val BASE_URL = "https://newsapi.ai/api/v1/article/getArticles/"
        const val API_KEY = "0d077d3b-0d32-49ea-968a-f3fe1e73405d"
    }

    @GET("?query=%7B%22%24query%22%3A%7B%22%24and%22%3A%5B%7B%22locationUri%22%3A%22http%3A%2F%2Fen.wikipedia.org%2Fwiki%2FCzech_Republic%22%7D%2C%7B%22dateStart%22%3A%222023-12-03%22%2C%22dateEnd%22%3A%222023-12-10%22%2C%22lang%22%3A%22ces%22%7D%5D%7D%2C%22%24filter%22%3A%7B%22isDuplicate%22%3A%22skipDuplicates%22%7D%7D&resultType=articles&articlesSortBy=date&includeArticleCategories=true&includeArticleImage=true&includeSourceTitle=false&includeSourceLocation=true")
    suspend fun getTopNews(
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsResponse
}