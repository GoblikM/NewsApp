package com.example.newsapp.presentation.shared_components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedIcon(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isClicked by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 2f else 1f,
        animationSpec = spring(Spring.DampingRatioMediumBouncy),
        label = ""
    )
    LaunchedEffect(key1 = scale) {
        delay(100)
        isClicked = false
    }

    Icon(imageVector = imageVector,
        contentDescription = contentDescription,
        tint = if (isClicked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .size(20.dp)
            .clickable {
                isClicked = !isClicked
                onClick()
            }
            .graphicsLayer(
                scaleX = scale, scaleY = scale
            ))
}
