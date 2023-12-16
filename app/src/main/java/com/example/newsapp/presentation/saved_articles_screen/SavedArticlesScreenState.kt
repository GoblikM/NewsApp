package com.example.newsapp.presentation.saved_articles_screen

import com.example.newsapp.data.database.ArticleEntity

data class SavedArticlesScreenState(
    val isLoading: Boolean = false,
    val articles: List<ArticleEntity> = emptyList(),
    val error: String? = null,
)



