package com.example.tse.news.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tse.news.model.Article
import com.example.tse.news.model.SourceIdName
import com.example.tse.news.model.SourceUser


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: List<Article>)

    @Query("SELECT * FROM articles")
    fun getListNews(): DataSource.Factory<Int, Article>

    @Query("SELECT id, name FROM articles")
    fun getListSources(): List<SourceIdName>
}