package com.example.tse.news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import android.util.Log
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.Article
import com.example.tse.news.model.NewsLocalCache

class ArticleBoundaryCallback(
        private val service: NewsService,
        private val cache: NewsLocalCache
) : PagedList.BoundaryCallback<Article>() {

    private val TOPIC = "apple"
    private val API_KEY = "94294f4227bf4600849e1697d6a48ec1"
    private val SOURCES = "abc-news"
    private val LANGUAGE = "en"

    private val TAG: String = ArticleBoundaryCallback::class.java.simpleName

    // Keep the last requested page.
    // When the request is successful, increment the page number.
    private var lastRequestPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiples requests in the same time
    private var isRequestInProgress = false

    companion object {
        private const val NETWORK_PASE_SIZE = 30
    }

    override fun onZeroItemsLoaded() {
        requestAndSaveData(TOPIC, API_KEY, SOURCES, LANGUAGE)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        requestAndSaveData(TOPIC, API_KEY, SOURCES, LANGUAGE)
    }


    private fun requestAndSaveData(country_name: String, apiKey: String, sources: String, language: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        Log.e(TAG, "Val primera vez: $lastRequestPage")
        searchNews(service, country_name,apiKey, sources, language, lastRequestPage, NETWORK_PASE_SIZE, {
            articles -> cache.insert(articles, {

            lastRequestPage++
            Log.e(TAG, "Val: $lastRequestPage")
            isRequestInProgress = false
        })
        },{
            error -> _networkErrors.postValue(error)
            isRequestInProgress = false
        })


    }
}