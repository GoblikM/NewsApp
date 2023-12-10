package com.example.newsapp.presentation.article_detail_screen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.shared_components.ImageHolder

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
        ImageHolder(size =350.dp, imgUrl = "ff")
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