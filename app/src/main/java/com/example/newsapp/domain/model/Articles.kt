package com.example.newsapp.domain.model

data class Articles(
    val count: Int?,
    val page: Int?,
    val pages: Int?,
    val results: List<Result>?,
    val totalResults: Int?
)