package com.example.tse.news.model

import android.util.Log
import androidx.paging.DataSource
import com.example.tse.news.database.ArticleDao
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