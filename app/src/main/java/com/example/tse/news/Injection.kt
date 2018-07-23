package com.example.tse.news

import androidx.lifecycle.ViewModelProvider
import android.content.Context
import com.example.tse.news.api.NewsService
import com.example.tse.news.database.ArticleDatabase
import com.example.tse.news.model.NewsLocalCache
import com.example.tse.news.repository.ArticleRepository
import com.example.tse.news.ui.ViewModelFactory
import java.util.concurrent.Executors

object Injection {

    private fun provideCache(context: Context): NewsLocalCache{
        val database = ArticleDatabase.getInstance(context)
        return NewsLocalCache(database.articlesDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideNews(context: Context): ArticleRepository{
        return ArticleRepository(NewsService.create(), provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory{
        return ViewModelFactory(provideNews(context))
    }
}