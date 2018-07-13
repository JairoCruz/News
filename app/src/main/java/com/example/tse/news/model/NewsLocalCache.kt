package com.example.tse.news.model

import android.arch.lifecycle.LiveData
import android.util.Log
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

    fun articlesByCountry(): LiveData<List<Article>> {
        return articleDao.getArticles()
    }

}