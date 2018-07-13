package com.example.tse.news.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "articles", primaryKeys = ["title", "publishedAt"])
data class Article(
        val author: String?,
        val title: String,
        val url: String,
        val urlToImage: String?,
        val publishedAt: Date
        )