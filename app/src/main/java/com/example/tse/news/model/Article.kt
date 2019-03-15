package com.example.tse.news.model

import androidx.room.*
import java.util.*

data class ArticleSource(
        val id: String,
        val name: String
)


@Entity(tableName = "articles", primaryKeys = arrayOf("title"),foreignKeys = arrayOf(ForeignKey(
        entity = Source::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("source_id"),
        onDelete = ForeignKey.CASCADE
)), indices = arrayOf(Index(value = ["source_id"])))

data class Article(
        @Embedded val source: ArticleSource,
        val author: String?,
        val title: String,
        val description: String?,
        val url: String,
        val urlToImage: String?,
        val publishedAt: Date,
        @ColumnInfo(name = "source_id")
        val sourceId: String?
        ){
        override fun toString(): String {
                return "Article(source=$source, author=$author, title='$title', description=$description, url='$url', urlToImage=$urlToImage, publishedAt=$publishedAt, sourceId=$sourceId)"
        }
}