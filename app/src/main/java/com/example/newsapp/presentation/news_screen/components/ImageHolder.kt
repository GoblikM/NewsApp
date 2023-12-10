package com.example.newsapp.presentation.news_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.newsapp.R

@Composable
fun ImageHolder(
    size: Dp
){
    Image(
        painter = painterResource(id = R.drawable.test_img),
        contentDescription = "test-image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(size)
    )

}