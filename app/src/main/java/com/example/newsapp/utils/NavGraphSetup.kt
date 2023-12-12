package com.example.newsapp.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.presentation.article_detail_screen.ArticleDetailScreen
import com.example.newsapp.presentation.article_detail_screen.ArticleDetailViewModel
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
        composable(route = Screen.NewsScreen.route,
            ){
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(
               state = viewModel.state,
                onCardClicked = {result->
                    Log.d("SentUri", "NavGraphSetup: ${result.uri}")
                    navController.navigate(Screen.ArticleScreen.route + result.uri)
                }
            )
        }
        composable(
            route = Screen.ArticleScreen.route,
            arguments = listOf(
                navArgument("uri"){type = NavType.StringType})
        ){ backStackEntry ->
            val uri = backStackEntry.arguments?.getString("uri")
            val viewModel : ArticleDetailViewModel = hiltViewModel()
            Log.d("RecievedUri", "NavGraphSetup: $uri")
            ArticleDetailScreen(
                state = viewModel.state,
                articleURI = uri?: ""
            )

        }
    }

}
