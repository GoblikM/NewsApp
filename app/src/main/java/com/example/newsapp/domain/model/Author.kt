package com.example.newsapp.domain.model

data class Author(
    val isAgency: Boolean,
    val name: String,
    val type: String,
    val uri: String
)