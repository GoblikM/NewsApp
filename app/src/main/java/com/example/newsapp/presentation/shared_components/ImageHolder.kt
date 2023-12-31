package com.example.newsapp.presentation.shared_components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R

@Composable
fun ImageHolder(
    modifier: Modifier = Modifier,
    size: Dp,
    imgUrl: String?,
) {
    //val isLoading  = remember { mutableStateOf(true) }
    AsyncImage(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imgUrl)
            .crossfade(500)
            .build(),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(size),
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.no_img),
    /*  onLoading = {
            isLoading.value = true
        },
        onSuccess = {
            isLoading.value = false
        },
        onError = {
            isLoading.value = false
        }
    */

    )
/*  if(isLoading.value){
        Box(modifier = modifier){
            CircularProgressIndicator()
        }
    }
 */

    }

