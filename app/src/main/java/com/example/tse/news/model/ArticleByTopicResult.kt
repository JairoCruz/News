package com.example.tse.news.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

data class ArticleByTopicResult(
        val data: LiveData<PagedList<Article>>,
        val networkErrors: LiveData<String>
)