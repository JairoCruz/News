package com.example.tse.news.api

import com.example.tse.news.model.Source
import com.google.gson.annotations.SerializedName

data class SourcesNewsResponse(
        @SerializedName("sources") val sources: List<Source> = emptyList(),
        val total: Int = 0,
        val status: String? = null
)