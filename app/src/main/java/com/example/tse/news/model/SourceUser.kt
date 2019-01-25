package com.example.tse.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SourceUser")
data class SourceUser(
        @PrimaryKey
        val id: String,
        val name: String,
        val description: String,
        val url: String,
        val category: String,
        val language: String,
        val country: String
)