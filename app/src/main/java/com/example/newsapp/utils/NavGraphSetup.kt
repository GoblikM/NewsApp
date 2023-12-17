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
import com.example.newsapp.presentation.original_article_screen.OriginalArticleScreen
import com.example.newsapp.presentation.saved_articles_screen.SavedArticlesScreen
import com.example.newsapp.presentation.saved_articles_screen.SavedArticlesScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController,
    newsScreenViewModel: NewsScreenViewModel = hiltViewModel()
){
    val argKey = "url"
    NavHost(
        navController = navController,
        startDestination = Screen.NewsScreen.route
    ){
        composable(route = Screen.NewsScreen.route,
            ){
            val viewModel: NewsScreenViewModel = newsScreenViewModel
            NewsScreen(
               state = viewModel.state,
                onCardClicked = {result->
                    Log.d("SentUri", "NavGraphSetup: ${result.uri}")
                    navController.navigate(Screen.ArticleScreen.route + result.uri)
                },
                onSavedArticlesClicked = {
                    navController.navigate(Screen.SavedArticlesScreen.route)
                },
                onEvent = viewModel::onEvent

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
                onReturnBntClicked = {
                    navController.popBackStack()
                },
                onFloatingButtonClicked = {url->
                    navController.navigate("originalArticle_screen?$argKey=$url")
                },
            )

        }
        composable(
            route = Screen.SavedArticlesScreen.route
        ){
            val viewModel : SavedArticlesScreenViewModel = hiltViewModel()
            SavedArticlesScreen(
                state = viewModel.state,
                onReturnBntClicked = {
                    navController.popBackStack()
                },
                onEvent = viewModel::onEvent,
                onShowArticleClicked = {url->
                    navController.navigate("originalArticle_screen?$argKey=$url")
                }
            )

        }
        composable(
            route = "originalArticle_screen?$argKey={url}",
            arguments = listOf(
                navArgument(argKey){type = NavType.StringType}
            )
        ){
            navBackStackEntry ->
            OriginalArticleScreen(
                url = navBackStackEntry.arguments?.getString(argKey),
                onReturnBntClicked = {
                    navController.popBackStack()
                }
            )

        }
    }

}
