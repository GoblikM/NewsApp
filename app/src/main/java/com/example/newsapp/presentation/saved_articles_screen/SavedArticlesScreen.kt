package com.example.newsapp.presentation.saved_articles_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.newsapp.R
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.presentation.news_screen.components.NewsArticleCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedArticlesScreen(
    state: SavedArticlesScreenState,
    onCardClicked: (Result) -> Unit,
    onReturnBntClicked: () -> Unit,

) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = Modifier
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        .fillMaxSize(),
        topBar = {
            TopAppBar(scrollBehavior = scrollBehavior,
                title = {
                    Text(
                    text = stringResource(id = R.string.saved_news),
                    style = MaterialTheme.typography.titleMedium,
                )},
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

        content = { padding ->
            SavedArticlesList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                state = state,
                onCardClicked = onCardClicked
            )
        }
    )
}


/* TODO */
@Composable
fun SavedArticlesList(
    modifier: Modifier = Modifier,
    state: SavedArticlesScreenState,
    onCardClicked: (Result) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(state.articles) { article ->
            NewsArticleCard(
                article = article,
                onCardClicked = onCardClicked
            )
        }
    }
}