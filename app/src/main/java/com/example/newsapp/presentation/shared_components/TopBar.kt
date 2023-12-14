package com.example.newsapp.presentation.shared_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
    onSearchIconClicked: () -> Unit,
    onMenuIconClicked: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        actions = {
            IconButton(onClick = onSearchIconClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuIconClicked) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopBarPreview() {
    TopBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        title = "News",
        onSearchIconClicked = {},
        onMenuIconClicked = {}
    )
}