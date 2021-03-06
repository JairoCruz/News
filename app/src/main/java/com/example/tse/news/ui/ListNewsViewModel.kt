package com.example.tse.news.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.tse.news.model.Article
import com.example.tse.news.model.ArticleByTopicResult
import com.example.tse.news.repository.ArticleRepository

class ListNewsViewModel(private val repository: ArticleRepository) : ViewModel() {

    private val queryListSource = MutableLiveData<String>()

    // private val articleResult: ArticleByTopicResult = repository.listNews()

    private val articleResult: LiveData<ArticleByTopicResult> = Transformations.map(queryListSource){
        repository.listNews(it)
    }

    //val articles: LiveData<PagedList<Article>> = articleResult.data

   // val networErrors: LiveData<String> = articleResult.networkErrors

    val articles: LiveData<PagedList<Article>> = Transformations.switchMap(articleResult){ it.data}

    val networErrors: LiveData<String> = Transformations.switchMap(articleResult) {
        it.networkErrors
    }


    fun searchN(queryList: String){
        queryListSource.value = queryList
    }





}


