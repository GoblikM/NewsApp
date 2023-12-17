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
import com.example.newsapp.domain.model.articleDetailResponse.Info
import com.example.newsapp.presentation.shared_components.ImageHolder
import com.example.newsapp.utils.formatDate

@Composable
fun ArticleDetail(
    article : Info,
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
            text = "",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.tertiary
            )
        )
        Text(
            text = article.title,
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
                text = article.authors.firstOrNull()?.name ?: "Autor neznámý",
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
            Text(text = formatDate(article.date),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )

        }
        Text(
            text = "",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.padding(8.dp))
        ImageHolder(size =350.dp, imgUrl = article.image)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = article.body,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,

            )

    }


}