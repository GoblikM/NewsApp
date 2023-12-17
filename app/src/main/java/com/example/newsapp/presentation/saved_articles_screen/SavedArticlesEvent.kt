package com.example.newsapp.presentation.saved_articles_screen

import com.example.newsapp.data.database.ArticleEntity

sealed class SavedArticlesEvent {
    data class OnSwipeToDelete(val article: ArticleEntity) : SavedArticlesEvent()
}