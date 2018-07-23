package com.example.tse.news.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import android.util.Log
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.ArticleByTopicResult
import com.example.tse.news.model.NewsLocalCache

class ArticleRepository (private val service: NewsService, private val cache: NewsLocalCache){

    private val TAG: String = ArticleRepository::class.java.simpleName

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

    /**
    * Serach news
    * */
    fun newsByCountry(country: String, apiKey: String): ArticleByTopicResult {
         Log.d(TAG, "Country: $country")
        // Get data soruce factory from the local cache
        val dataSourceFactory = cache.articlesByCountry()

        // Construct the boundary callback
        val boundaryCallback = ArticleBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data2 = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        return ArticleByTopicResult(data2, networkErrors)
    }


}