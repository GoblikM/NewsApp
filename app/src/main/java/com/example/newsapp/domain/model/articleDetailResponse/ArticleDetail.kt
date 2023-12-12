package com.example.newsapp.domain.model.articleDetailResponse

import com.google.gson.annotations.SerializedName

data class ArticleDetail(
    @SerializedName("info")
    val info: Info
)
