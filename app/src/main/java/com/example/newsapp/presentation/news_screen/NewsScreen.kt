package com.example.newsapp.presentation.news_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.presentation.news_screen.components.NewsArticleCard
import com.example.newsapp.presentation.shared_components.TopBar
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onCardClicked: (Result) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            /*TODO  Make a custom drawer content composable fun  */
            ModalDrawerSheet {
                Text("NewsApp", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Uložené zprávy") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }
        }) {

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopBar(
                    scrollBehavior = scrollBehavior,
                    title = "News",
                    onSearchIconClicked = {},
                    onMenuIconClicked = { scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                    }
                )
            },
            floatingActionButton = {
                // Use the LazyListState to scroll to the top
                val density = LocalDensity.current.density
                FloatingActionButton(onClick = {
                    scope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll to top"
                    )
                }
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
                        lazyListState = lazyListState,
                    )

                }
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun NewsScreenPreview() {
    NewsAppTheme {
        NewsScreen(state = NewsScreenState(), onCardClicked = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun NewsScreenPreviewNightMode() {
    NewsAppTheme {
        NewsScreen(state = NewsScreenState(), onCardClicked = {})
    }
}

@Composable
fun NewsList(
    state: NewsScreenState,
    onCardClicked: (Result) -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            items(state.articles) { article ->

                NewsArticleCard(
                    article = article,
                    onCardClicked = onCardClicked
                )
            }

        }
    }
}









