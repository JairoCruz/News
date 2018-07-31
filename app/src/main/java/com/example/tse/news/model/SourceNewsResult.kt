package com.example.tse.news.model

import androidx.lifecycle.LiveData

data class SourceNewsResult(
        val data: LiveData<List<Source>>,
        val networkErrors: LiveData<String>
)