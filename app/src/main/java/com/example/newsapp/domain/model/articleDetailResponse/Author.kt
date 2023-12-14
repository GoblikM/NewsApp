package com.example.newsapp.domain.model.articleDetailResponse

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String,
    @SerializedName("isAgency")
    val isAgency: Boolean
)