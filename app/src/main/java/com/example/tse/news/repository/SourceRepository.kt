package com.example.tse.news.repository

import androidx.lifecycle.MutableLiveData
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.getSourcesNews
import com.example.tse.news.model.SourceNewsResult
import com.example.tse.news.model.SourcesLocalCache

class SourceRepository(private val service: NewsService, private val cache: SourcesLocalCache) {

    private val TAG: String = SourceRepository::class.java.simpleName

    private val networkErrors = MutableLiveData<String>()

    private var isRequestInProgress = false

    fun getSource(apiKey: String): SourceNewsResult {
        requestAndSaveData(apiKey)

        val data = cache.sourcesNews()

        return SourceNewsResult(data, networkErrors)
    }

    private fun requestAndSaveData(apiKey: String){
        if (isRequestInProgress) return

        isRequestInProgress = true

        getSourcesNews(service, apiKey, { sources ->
            cache.insert(sources, {
                isRequestInProgress = false
            })
        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }




}