package com.example.newsapp.presentation.article_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.news_screen.components.ImageHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    title: String,

) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {Text(text = title)},
                actions = {
                          IconButton(onClick = { /*TODO*/ }) {
                              Icon(
                                  imageVector = Icons.Default.ArrowBack,
                                  contentDescription = "Back"
                              )

                          }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )

            )

        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
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
             ArticleDetail(scrollState)
         }


     })

}

@Composable
fun ArticleDetail(
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "CATEGORY",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.tertiary
            )
        )
        Text(
            text = "Tatum and Doncic clash in Boston-Dallas matchup",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start

        )
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,

        ) {
            Text(
                text = "Author",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            //add dot

            Text(
                text = "•",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.secondary),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Date",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )

        }
        Text(
            text = "After crashes, scandals, and SBF’s guilty verdict, " +
                    "many hoped the crypto industry would grow up. " +
                    "Speculation around the arrival of a spot bitcoin ETF shows " +
                    "old hype dies hard.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ImageHolder(350.dp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text ="The prospect that US residents may soon be able to invest in " +
                    "bitcoin through their brokerage, as if it were a regular stock, " +
                    "has prompted a fresh round of hype in crypto circlesand a surge in crypto " +
                    "The prospect that US residents may soon be able to invest in " +
                    "bitcoin through their brokerage, as if it were a regular stock, " +
                    "has prompted a fresh round of hype in crypto circlesand a surge in crypto fwesfjspfj wegopjwepgj ",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,

        )

    }


}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
fun ArticleDetailScreenPreview(){
    ArticleDetailScreen(
        title = "Article"
    )
}