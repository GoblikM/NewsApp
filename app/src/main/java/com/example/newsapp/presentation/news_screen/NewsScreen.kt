package com.example.newsapp.presentation.news_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.Greeting
import com.example.newsapp.R

@Composable
fun NewsScreen(

){

}

@Composable
fun NewsList(
    modifier : Modifier = Modifier,
    ){
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

@Composable
@Preview
fun NewsListPreview(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
       NewsList()
    }

}

@Composable
fun NewsArticleCard(
    modifier : Modifier = Modifier,
    ){
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.clickable {  },
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                Text(
                    text = "CATEGORY",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.primary),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Tatum and Doncic clash in Boston-Dallas matchup",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 3

                )
                Spacer(modifier = Modifier.padding(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Source",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    //add dot

                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary),
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Date",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.secondary),
                    )

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ImageHolder()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp),
                        )
                    }



                }

            }

        }




    }


}



@Composable
fun ImageHolder(

){
    Image(
        painter = painterResource(id = R.drawable.test_img),
        contentDescription = "test-image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(120.dp)
    )

}

@Composable
@Preview
fun ImageHolderPreview(){
    ImageHolder()

}

@Composable
@Preview
fun NewsArticleCardPreview(){
    NewsArticleCard()

}