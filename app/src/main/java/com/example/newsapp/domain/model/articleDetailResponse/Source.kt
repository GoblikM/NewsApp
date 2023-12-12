package com.example.newsapp.domain.model.articleDetailResponse

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("dataType")
    val dataType: String,
    @SerializedName("title")
    val title: String
)