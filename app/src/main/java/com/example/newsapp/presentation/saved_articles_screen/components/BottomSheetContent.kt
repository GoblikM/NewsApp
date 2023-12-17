package com.example.newsapp.presentation.saved_articles_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.database.ArticleEntity
import com.example.newsapp.presentation.shared_components.ImageHolder
import com.example.newsapp.utils.formatDate

@Composable
fun BottomSheetContent(
    article : ArticleEntity,
    onShowArticleClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatDate(article.date) ,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = article.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = article.authors ?: "Autor neznámý",
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = article.source ?: "",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ImageHolder(size = 250.dp, imgUrl = article.image )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onShowArticleClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Přejít na článek")
            }
            Spacer(modifier = Modifier.height(10.dp))



        }
        
    }

}



