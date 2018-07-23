package com.example.tse.news.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class ArticleByTopicResult(
        val data: LiveData<PagedList<Article>>,
        val networkErrors: LiveData<String>
)