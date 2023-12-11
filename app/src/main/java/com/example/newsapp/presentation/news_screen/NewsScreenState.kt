package com.example.newsapp.presentation.news_screen

import com.example.newsapp.domain.model.Result

data class NewsScreenState(
    val isLoading: Boolean = false,
    val articles: List<Result> = emptyList(),
    val error: String? = null,
    val isSearchBarVisible: Boolean = false,
    val selectedArticle: Result? = null,
)