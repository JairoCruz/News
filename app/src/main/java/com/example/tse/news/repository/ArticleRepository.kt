package com.example.tse.news.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import android.util.Log
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.ArticleByTopicResult
import com.example.tse.news.model.NewsLocalCache
import com.example.tse.news.model.SourceIdName
import com.example.tse.news.model.SourcesUserLocalCache

class ArticleRepository (private val service: NewsService, private val cache: NewsLocalCache){

    private val TAG: String = ArticleRepository::class.java.simpleName
    //private var boundaryCallback: ArticleBoundaryCallback
    //private var boundaryCallback: ArticleBoundaryCallback = ArticleBoundaryCallback(service, cache)

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

    init {
        // Initialize boundaryCallback
       // boundaryCallback = ArticleBoundaryCallback(service, cache)
    }

    /**
    * Get List News
    * */
    fun listNews(sourcesList: String): ArticleByTopicResult {

        //Log.e(TAG, "De lista de fuentes: " + cache.listSources().size)

        // Get data soruce factory from the local cache
        val dataSourceFactory = cache.listNews(/*sourcesList*/)


        // Construct the boundary callback
        val boundaryCallback = ArticleBoundaryCallback(service, cache,sourcesList)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data2 = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        return ArticleByTopicResult(data2, networkErrors)
    }

    /**
     * Call first request data from News
     */
    fun requestNewsUser(listSourceUser: String){
      // boundaryCallback.requestNewsUser(listSourceUser)
    }

    fun listSource(){
       // Log.e("TAG", "V: " + boundaryCallback.requestListSource().size)
    }



}