package com.example.newsapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class ArticleEntity(
    val authors: String?,
    val body: String?,
    val dataType: String,
    val date: String,
    val dateTime: String,
    val dateTimePub: String,
    val eventUri: String?,
    val image: String,
    val isDuplicate: Boolean,
    val lang: String,
    val relevance: Int,
    val sentiment: Double,
    val sim: Double,
    val source: String?,
    val time: String,
    val title: String,
    @PrimaryKey
    val uri: String,
    val url: String,
    val wgt: Int
)


