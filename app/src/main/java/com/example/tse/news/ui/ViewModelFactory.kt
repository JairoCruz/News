package com.example.tse.news.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.tse.news.repository.ArticleRepository

class ViewModelFactory(private val repository: ArticleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListNewsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListNewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}