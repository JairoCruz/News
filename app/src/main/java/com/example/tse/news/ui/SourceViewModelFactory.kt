package com.example.tse.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tse.news.repository.SourceRepository

class SourceViewModelFactory(private val repository: SourceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListSourcesNewsViewModel::class.java)){
            return ListSourcesNewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}