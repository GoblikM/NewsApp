package com.example.newsapp.presentation.saved_articles_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.database.ArticleEntity
import com.example.newsapp.presentation.shared_components.ImageHolder
import com.example.newsapp.utils.formatDate

@Composable
fun SavedArticlesCard(
    article: ArticleEntity,
    modifier: Modifier = Modifier,
    onCardClicked: (ArticleEntity) -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .clickable { onCardClicked(article) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,

                ) {
                Text(
                    text = article.source?.uppercase() ?: "Neuveden",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 16.dp),
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
                ImageHolder(size = 120.dp, imgUrl = article.image, modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom

                ) {

                    Text(
                        text = formatDate(article.date),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary
                        ),
                    )
                }


            }
        }


    }


}


