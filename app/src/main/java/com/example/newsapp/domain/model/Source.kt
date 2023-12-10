package com.example.newsapp.domain.model

data class Source(
    val dataType: String,
    val location: Location,
    val locationValidated: Boolean,
    val uri: String
)