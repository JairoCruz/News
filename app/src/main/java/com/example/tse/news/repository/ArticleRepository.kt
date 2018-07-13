package com.example.tse.news.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.util.Log
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.ArticleByTopicResult
import com.example.tse.news.model.NewsLocalCache

class ArticleRepository (private val service: NewsService, private val cache: NewsLocalCache){

    private val TAG: String = ArticleRepository::class.java.simpleName
    private val TOPIC = "apple"
    private val API_KEY = "94294f4227bf4600849e1697d6a48ec1"
    // Keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestPage = 1
    private var pageSize = 20

    //private val networkErrors = MutableLiveData<String>()

   // private var isRequestInProgress = false

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
        val data2 = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        // Get data from api and save on local DB
        //requestAndSaveData(country, apiKey)

        // Get data from the local cache
        //val data = cache.articlesByCountry()

        return ArticleByTopicResult(data2, networkErrors)
    }

//    private fun requestAndSaveData(country_name: String, apiKey: String) {
//        if (isRequestInProgress) return
//
//        isRequestInProgress = true
//        Log.e(TAG, "Val primera vez: $lastRequestPage")
//        searchNews(service, country_name,apiKey, lastRequestPage, pageSize, {
//            articles -> cache.insert(articles, {
//
//            lastRequestPage++
//            Log.e(TAG, "Val: $lastRequestPage")
//            isRequestInProgress = false
//        })
//        },{
//            error -> networkErrors.postValue(error)
//            isRequestInProgress = false
//        })
//
//
//    }

//    fun requestMoreData(){
//        requestAndSaveData(TOPIC, API_KEY)
//    }



}