package com.example.tse.news.api

import com.example.tse.news.model.Article
import com.google.gson.annotations.SerializedName

/*
* Data class to hold news responses from NewsService API calls.
* */
data class NewsByTopicResponse(
        @SerializedName("totalResults") val total: Int = 0,
        @SerializedName("articles") val articles: List<Article> = emptyList(),
        val status: String? = null
)
