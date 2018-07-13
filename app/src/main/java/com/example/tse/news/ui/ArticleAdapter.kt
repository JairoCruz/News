package com.example.tse.news.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup

import com.example.tse.news.model.Article


class ArticleAdapter : PagedListAdapter<Article, RecyclerView.ViewHolder>(A_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val artItem = getItem(position)

        if (artItem != null){
            (holder as ArticleViewHolder).bind(artItem)
        }
    }

    companion object {
        private val A_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article?, newItem: Article?): Boolean =  oldItem?.title == newItem?.title


            override fun areContentsTheSame(oldItem: Article?, newItem: Article?): Boolean =
                    oldItem == newItem
        }
    }

}