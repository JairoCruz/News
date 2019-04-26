package com.example.tse.news.repository

import androidx.paging.LivePagedListBuilder
import com.example.tse.news.api.NewsService
import com.example.tse.news.model.ArticleByTopicResult
import com.example.tse.news.model.NewsLocalCache

class ArticleRepository (private val service: NewsService, private val cache: NewsLocalCache){

    private val TAG: String = ArticleRepository::class.java.simpleName

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

    /**
    * Get List News
    * */
    fun listNews(sourcesList: String): ArticleByTopicResult {

        //Log.e(TAG, "De lista de fuentes: " + cache.listSources().size)

        // Get data soruce factory from the local cache
        val dataSourceFactory = cache.listNews()


        // Construct the boundary callback
        val boundaryCallback = ArticleBoundaryCallback(service, cache,sourcesList)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data2 = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        return ArticleByTopicResult(data2, networkErrors)
    }


}