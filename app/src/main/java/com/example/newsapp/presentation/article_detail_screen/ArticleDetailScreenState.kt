package com.example.newsapp.presentation.article_detail_screen

import com.example.newsapp.domain.model.articleDetailResponse.Info

data class ArticleDetailScreenState(
    val isLoading: Boolean = false,
    val article: Info? = null,
    val error: String? = null,
)