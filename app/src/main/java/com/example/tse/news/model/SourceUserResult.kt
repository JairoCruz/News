package com.example.tse.news.model

import androidx.lifecycle.LiveData

data class SourceUserResult(
        val data: LiveData<List<SourceUser>>
)