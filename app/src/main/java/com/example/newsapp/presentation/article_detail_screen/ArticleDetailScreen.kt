package com.example.newsapp.presentation.article_detail_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.newsapp.presentation.article_detail_screen.components.ArticleDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    state: ArticleDetailScreenState,
    onReturnBntClicked: () -> Unit,
    onFloatingButtonClicked: (String) -> Unit
    ) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = Modifier
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        .fillMaxSize(),
        topBar = {
            TopAppBar(scrollBehavior = scrollBehavior, title = { Text(text = state.article?.source?.title?.uppercase() ?: "") },
                actions = {
                IconButton(onClick = { onReturnBntClicked() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                    )

                }
            }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            )

            )

        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.article?.let { onFloatingButtonClicked(it.url) }

            }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "To Url",


                    )

            }
        },

        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                state.article?.let {
                    ArticleDetail(
                        article = it,
                        scrollState = scrollState)
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                state.isLoading.let {
                    if (it) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(alignment = Alignment.Center))
                    }
                }
            }


        })

}


