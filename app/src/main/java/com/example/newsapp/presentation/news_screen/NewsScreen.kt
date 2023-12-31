package com.example.newsapp.presentation.news_screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.presentation.news_screen.components.MenuDrawer
import com.example.newsapp.presentation.news_screen.components.NewsArticleCard
import com.example.newsapp.presentation.shared_components.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onCardClicked: (Result) -> Unit,
    onSavedArticlesClicked: () -> Unit,
    onEvent: (NewsScreenEvent) -> Unit

) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = {
            state.copy(isRefreshing = true)
            onEvent(NewsScreenEvent.onRefresh)
        }
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawer(
                onSavedArticlesClicked = {
                    onSavedArticlesClicked()
                    scope.launch {
                        drawerState.close()

                    }
                }
            )

        }) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,

                    )
            },
            topBar = {
                TopBar(
                    scrollBehavior = scrollBehavior,
                    title = stringResource(id = R.string.news_title),
                    onSearchIconClicked = {},
                    onMenuIconClicked = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                // Use the LazyListState to scroll to the top
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState)

                    ) {
                        NewsList(
                            state = state,
                            onCardClicked = onCardClicked,
                            lazyListState = lazyListState,
                            onEvent = onEvent,
                            showSnackBar = {
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "Článek uložen",
                                        actionLabel = "Zavřít",
                                        duration = SnackbarDuration.Short,
                                    )

                                }
                            }
                        )

                    PullRefreshIndicator(
                        refreshing = state.isRefreshing,
                        state = pullRefreshState,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                    )
                    }

                }
            }
        )
    }

}


@Composable
fun NewsList(
    state: NewsScreenState,
    onCardClicked: (Result) -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    onEvent: (NewsScreenEvent) -> Unit,
    showSnackBar: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        LazyColumn(
            state = lazyListState,
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            items(state.articles) { article ->

                NewsArticleCard(
                    article = article,
                    onCardClicked = onCardClicked,
                    onEvent = onEvent,
                    showSnackBar = showSnackBar,
                    onShareBtnClicked = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, article.url)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)

                    }
                )
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            if (state.error != null) {
                Text(
                    text = state.error,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}









