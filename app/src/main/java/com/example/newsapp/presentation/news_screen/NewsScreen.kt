package com.example.newsapp.presentation.news_screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.news_screen.components.NewsArticleCard
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsScreen(

){

}

@Composable
fun NewsList(
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
            items(10){
                NewsArticleCard()
            }

        }

    }


}

@Composable
@Preview(showBackground = true)
fun NewsListPreview(){
    NewsAppTheme {
       NewsList()
    }
}

// dark theme preview
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun NewsListPreviewDark(){
    NewsAppTheme {
        NewsList()
    }
}




