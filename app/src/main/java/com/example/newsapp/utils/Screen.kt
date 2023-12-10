package com.example.newsapp.utils

sealed class Screen(val route: String) {
    object NewsScreen: Screen("news_screen")
    object ArticleScreen: Screen("article_screen/{articleTitle}")
}