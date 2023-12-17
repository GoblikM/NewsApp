package com.example.newsapp.presentation.saved_articles_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapp.R
import com.example.newsapp.data.database.ArticleEntity
import com.example.newsapp.presentation.saved_articles_screen.components.BottomSheetContent
import com.example.newsapp.presentation.saved_articles_screen.components.SavedArticlesCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedArticlesScreen(
    state: SavedArticlesScreenState,
    onReturnBntClicked: () -> Unit,
    onEvent: (SavedArticlesEvent) -> Unit,
    onShowArticleClicked: (String) -> Unit

) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    if(shouldBottomSheetShow){
        ModalBottomSheet(
            onDismissRequest = { shouldBottomSheetShow = false },
            sheetState = sheetState,
        ) {
            state.selectedArticle?.let { article ->
                BottomSheetContent(
                    article = article,
                    onShowArticleClicked = {
                        onShowArticleClicked(article.url)
                        coroutineScope.launch{ sheetState.hide() }.invokeOnCompletion {
                            if(!sheetState.isVisible) shouldBottomSheetShow = false
                        }

                    }
                )

            }

        }
    }

    Scaffold(modifier = Modifier
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = stringResource(id = R.string.saved_news),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
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

        }

    ) { padding ->
        SavedArticlesList(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            state = state,
            onCardClicked = { article ->
                shouldBottomSheetShow = true
                onEvent(SavedArticlesEvent.onCardClicked(article))
            },
            onEvent = onEvent
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedArticlesList(
    modifier: Modifier = Modifier,
    state: SavedArticlesScreenState,
    onCardClicked: (ArticleEntity) -> Unit,
    onEvent: (SavedArticlesEvent) -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_saved_articles))
    val context = LocalContext.current.applicationContext

    LazyColumn(
        modifier = modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = state.articles,
            key = { article -> article.uri }
        ) { article ->
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                onEvent(SavedArticlesEvent.OnSwipeToDelete(article))
            }
            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                background = {
                    val backgroundColor by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.DismissedToStart -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.surface
                        }, label = ""
                    )
                    val iconScale by animateFloatAsState(
                        targetValue = if (dismissState.targetValue == DismissValue.Default) 1.3f else 0.5f,
                        label = ""
                    )
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color = backgroundColor)
                            .padding(end = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            modifier = Modifier.scale(iconScale),
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                },
                dismissContent = {
                    SavedArticlesCard(
                        article = article,
                        onCardClicked = onCardClicked
                    )

                }
            )

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            if (state.articles.isEmpty() && !state.isLoading) {
                LottieAnimation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f),
                    text = "Nemáte uložené žádné zprávy!",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

