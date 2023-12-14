package com.example.newsapp.domain.model.articleDetailResponse

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("lang")
    val lang: String,
    @SerializedName("isDuplicate")
    val isDuplicate: Boolean,
    @SerializedName("date")
    val date: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("dateTimePub")
    val dateTimePub: String,
    @SerializedName("dataType")
    val dataType: String,
    @SerializedName("sim")
    val sim: Double,
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("image")
    val image: String?,
    @SerializedName("eventUri")
    val eventUri: Any?,
    @SerializedName("eventTitle")
    val sentiment: Any?

)