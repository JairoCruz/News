package com.example.tse.news.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.example.tse.news.model.Article
import com.example.tse.news.model.ArticleByTopicResult
import com.example.tse.news.repository.ArticleRepository

class ListNewsViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val TOPIC = "apple"
    private val API_KEY = "94294f4227bf4600849e1697d6a48ec1"


    private val articleResult: ArticleByTopicResult = repository.newsByCountry(TOPIC, API_KEY)

    val articles: LiveData<PagedList<Article>> = articleResult.data

    val networErrors: LiveData<String> = articleResult.networkErrors

}