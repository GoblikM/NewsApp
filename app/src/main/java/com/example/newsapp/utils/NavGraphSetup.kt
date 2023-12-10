package com.example.newsapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.presentation.article_detail_screen.ArticleDetailScreen
import com.example.newsapp.presentation.news_screen.NewsScreen

@Composable
fun NavGraphSetup(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.NewsScreen.route
    ){
        composable(route = Screen.NewsScreen.route){
            NewsScreen()
        }
        composable(route = Screen.ArticleScreen.route){
            ArticleDetailScreen()
        }
    }

}