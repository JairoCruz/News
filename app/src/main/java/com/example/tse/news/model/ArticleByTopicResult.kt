package com.example.tse.news.model

import android.arch.lifecycle.LiveData

data class ArticleByTopicResult(
        val data: LiveData<List<Article>>,
        val networkErrors: LiveData<String>
)