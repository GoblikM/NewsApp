package com.example.newsapp.domain.model.newsResponse

data class Result(
    val authors: List<Author>,
    val body: String,
    val dataType: String,
    val date: String,
    val dateTime: String,
    val dateTimePub: String,
    val eventUri: String,
    val image: String,
    val isDuplicate: Boolean,
    val lang: String,
    val relevance: Int,
    val sentiment: Any,
    val sim: Double,
    val source: Source?,
    val time: String,
    val title: String,
    val uri: String,
    val url: String,
    val wgt: Int
)