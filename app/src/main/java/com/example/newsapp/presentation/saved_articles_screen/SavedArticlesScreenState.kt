package com.example.newsapp.presentation.saved_articles_screen

import com.example.newsapp.domain.model.newsResponse.Result

data class SavedArticlesScreenState(
    val isLoading: Boolean = false,
    val articles: List<Result> = emptyList(),
    val error: String? = null,
)



