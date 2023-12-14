package com.example.newsapp.presentation.news_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapp.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MenuDrawer(
    modifier : Modifier = Modifier,
    onSavedArticlesClicked: () -> Unit
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim))

    ModalDrawerSheet(
        modifier = Modifier

    ) {
        Column(
            modifier = modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            LottieAnimation(
                modifier = Modifier
                    .padding(16.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Fit
            )


            NavigationDrawerItem(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                icon = {
                    Text(
                        text = "📰",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                },
                label = {
                    Text(
                        text = "Uložené zprávy",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                },
                selected = false,
                onClick = { onSavedArticlesClicked() },

                )

            Divider()

        }
    }

}

@Composable
@Preview
fun MenuDrawerPreview() {
    MenuDrawer {}
}
