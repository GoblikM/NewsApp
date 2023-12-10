package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.utils.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                NavGraphSetup(navController = navController)
            }
        }
    }
}

