package com.example.tse.news.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tse.news.model.SourceUser
import com.example.tse.news.model.SourceUserResult
import com.example.tse.news.repository.SourceUserRepository

class ListSourceUserViewModel(private val repository: SourceUserRepository): ViewModel() {

    private val sourceUserResult: SourceUserResult = repository.getSourcesUser()

    val sourcesUser: LiveData<List<SourceUser>> = sourceUserResult.data

    fun insertSourceUser(sourcesUser: List<SourceUser>): Unit {
        repository.saveDataSourceUser(sourcesUser)
    }

}