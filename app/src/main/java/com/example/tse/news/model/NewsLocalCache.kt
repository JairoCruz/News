package com.example.tse.news.model

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import android.util.Log
import com.example.tse.news.database.ArticleDao
import com.example.tse.news.database.ArticleDatabase
import com.example.tse.news.database.SourceUserDao
import java.util.concurrent.Executor

class NewsLocalCache(private val articleDao: ArticleDao, private val ioExcecutor: Executor) {

    val TAG: String = NewsLocalCache::class.java.simpleName

    fun insert(articles: List<Article>, insertFinished: () -> Unit){
        ioExcecutor.execute {
            Log.e(TAG, "Inserting ${articles.size} articles")
            articleDao.insert(articles)
            insertFinished()
        }
    }

    fun listNews(): DataSource.Factory<Int, Article> {
        return articleDao.getListNews()
    }

    fun listSources(): List<SourceIdName>{
        return articleDao.getListSources()
    }

}