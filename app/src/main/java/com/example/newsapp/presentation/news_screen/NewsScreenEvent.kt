package com.example.newsapp.presentation.news_screen

import com.example.newsapp.domain.model.newsResponse.Result

sealed class NewsScreenEvent {
    data class OnSaveArticleClicked(val article: Result) : NewsScreenEvent()
    object onRefresh : NewsScreenEvent()

}