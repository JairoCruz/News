package com.example.tse.news.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tse.news.model.Source
import com.example.tse.news.model.SourceNewsResult
import com.example.tse.news.repository.SourceRepository

class ListSourcesNewsViewModel(private val repository: SourceRepository) : ViewModel() {
    private val API_KEY = "94294f4227bf4600849e1697d6a48ec1"

    private val sourcesNewsResult: SourceNewsResult = repository.getSource(API_KEY)

    val sources: LiveData<List<Source>> = sourcesNewsResult.data

    val networErrors: LiveData<String> = sourcesNewsResult.networkErrors
}