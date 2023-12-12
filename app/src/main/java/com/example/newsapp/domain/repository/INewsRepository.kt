package com.example.newsapp.domain.repository

import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.utils.Resource

interface INewsRepository {
    suspend fun getNews() :Resource<List<Result>>
}