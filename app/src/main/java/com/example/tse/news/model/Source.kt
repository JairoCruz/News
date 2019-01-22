package com.example.tse.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sources")
data class Source(
        @PrimaryKey
        val id: String,
        val name: String,
        val description: String,
        val url: String,
        val category: String,
        val language: String,
        val country: String,
        var isChecked: Boolean = false


)