package com.example.newsapp.utils

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.presentation.article_detail_screen.ArticleDetailScreen
import com.example.newsapp.presentation.news_screen.NewsScreen
import com.example.newsapp.presentation.news_screen.NewsScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.NewsScreen.route
    ){
        composable(route = Screen.NewsScreen.route){
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(
               state = viewModel.state,
                onCardClicked = {article ->
                    navController.navigate(Screen.ArticleScreen.route + article.title)
                }
            )
        }
        composable(route = Screen.ArticleScreen.route){
            ArticleDetailScreen()
        }
    }

}