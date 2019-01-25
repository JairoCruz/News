package com.example.tse.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tse.news.repository.SourceUserRepository
import java.lang.IllegalArgumentException

class SourceUserViewModelFactory(private val repository: SourceUserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListSourceUserViewModel::class.java)){
            return ListSourceUserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}