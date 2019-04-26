package com.example.tse.news.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.Article
import com.example.tse.news.model.NewsLocalCache

class ArticleBoundaryCallback(
        private val service: NewsService,
        private val cache: NewsLocalCache,
        private val sourcesList: String
) : PagedList.BoundaryCallback<Article>() {


    private val API_KEY = "94294f4227bf4600849e1697d6a48ec1"
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
        requestAndSaveData(API_KEY, sourcesList, LANGUAGE)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        requestAndSaveData(API_KEY, sourcesList, LANGUAGE)
    }


    private fun requestAndSaveData(apiKey: String, sources: String, language: String) {

        if (isRequestInProgress) return
        //Log.e(TAG, "Pedir datos de: $sources")

        isRequestInProgress = true

        // Log.e(TAG, "Val primera vez: $lastRequestPage")
        // Log.e(TAG, "lista de fuentes desde articulo: " + cache.listSources().size)

        searchNews(service, apiKey, sources, language, lastRequestPage, NETWORK_PASE_SIZE, {

            articles ->
            cache.insert(articles) {

                lastRequestPage++

              //  Log.e(TAG, "Val: $lastRequestPage")

                isRequestInProgress = false

            }
        }, { error ->
            _networkErrors.postValue(error)

            isRequestInProgress = false
        })


    }
}