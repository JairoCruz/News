package com.example.tse.news.model

import androidx.room.ColumnInfo

data class SourceIdName(
        @ColumnInfo(name="id") var Id: String?,
        @ColumnInfo(name="name") var name: String?
)