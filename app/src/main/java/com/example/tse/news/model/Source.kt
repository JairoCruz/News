package com.example.tse.news.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "Sources", indices = arrayOf(Index(value = ["id"])))
data class Source(
        @PrimaryKey
        @NonNull
        val id: String,
        val name: String,
        val description: String,
        val url: String,
        val category: String,
        val language: String,
        val country: String


)