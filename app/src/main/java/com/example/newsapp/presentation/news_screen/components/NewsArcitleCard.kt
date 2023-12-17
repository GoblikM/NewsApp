package com.example.newsapp.presentation.news_screen.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.presentation.news_screen.NewsScreenEvent
import com.example.newsapp.presentation.shared_components.AnimatedIcon
import com.example.newsapp.presentation.shared_components.ImageHolder
import kotlinx.coroutines.delay

@Composable
fun NewsArticleCard(
    article: Result,
    modifier: Modifier = Modifier,
    onCardClicked: (Result) -> Unit,
    onEvent: (NewsScreenEvent) -> Unit,
    showSnackBar: () -> Unit,
    onShareBtnClicked: () -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.clickable { onCardClicked(article) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,

                ) {
                Text(
                    text = article.source?.title?.uppercase() ?: "Neuveden",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom

                ) {

                    Text(
                        text = article.date,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary
                        ),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom

                ) {
                    AnimatedIcon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Save",
                        onClick = {
                            showSnackBar()
                            onEvent(NewsScreenEvent.OnSaveArticleClicked(article))
                        })


                    Spacer(modifier = Modifier.padding(8.dp))


                    AnimatedIcon(imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        onClick = { onShareBtnClicked() })


                }


            }
        }


    }


}




