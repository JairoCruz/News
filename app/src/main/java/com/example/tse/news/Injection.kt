package com.example.tse.news

import androidx.lifecycle.ViewModelProvider
import android.content.Context
import com.example.tse.news.api.NewsService
import com.example.tse.news.database.ArticleDatabase
import com.example.tse.news.model.NewsLocalCache
import com.example.tse.news.model.SourcesLocalCache
import com.example.tse.news.repository.ArticleRepository
import com.example.tse.news.repository.SourceRepository
import com.example.tse.news.ui.SourceViewModelFactory
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

    // SourcesNews
    private fun provideSourceCache(context: Context): SourcesLocalCache{
        val database = ArticleDatabase.getInstance(context)
        return SourcesLocalCache(database.sourceDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideSourcesNews(context: Context): SourceRepository{
        return SourceRepository(NewsService.create(), provideSourceCache(context))
    }

    fun provideViewModelFactorySourcesNews(context: Context): ViewModelProvider.Factory{
        return SourceViewModelFactory(provideSourcesNews(context))
    }


}