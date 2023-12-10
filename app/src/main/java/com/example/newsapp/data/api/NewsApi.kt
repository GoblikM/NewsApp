package com.example.newsapp.data.api

import com.example.newsapp.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "abcc21086334401a8e398578ddeb7e3e"
    }

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("country") countryCode: String = "us",
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsResponse
}