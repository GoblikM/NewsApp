package com.example.newsapp.presentation.news_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.domain.model.Result
import com.example.newsapp.presentation.news_screen.components.NewsArticleCard
import com.example.newsapp.presentation.shared_components.TopBar
import com.example.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onCardClicked: (Result) -> Unit
)
{
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                title = "News",
                onSearchIconClicked = {}
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                NewsList(
                    state = state,
                    onCardClicked = onCardClicked,
                )

            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun NewsScreenPreview(){
    NewsAppTheme {
        NewsScreen(state = NewsScreenState(), onCardClicked = {})
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun NewsScreenPreviewNightMode(){
    NewsAppTheme {
        NewsScreen(state = NewsScreenState(), onCardClicked = {})
    }
}

@Composable
fun NewsList(
    state: NewsScreenState,
    onCardClicked: (Result) -> Unit,
    modifier : Modifier = Modifier,
    ){
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ){
            items(state.articles){ article ->

                NewsArticleCard(
                    article = article,
                    onCardClicked = onCardClicked
                )
            }

        }
    }
}






