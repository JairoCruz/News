package com.example.tse.news.ui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tse.news.R
import com.example.tse.news.model.Article
import com.example.tse.news.utils.FormatDate
import kotlinx.android.synthetic.main.article_view.view.*

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.title)
    private val imageTitle: ImageView = view.findViewById(R.id.imageTitle)
    private val publicationDate: TextView  = view.findViewById(R.id.publicationDate)

    private var article: Article? = null

    fun bind(article: Article?){
        if (article != null){
            showArticle(article)
        }

    }

    private fun showArticle(article: Article){
        this.article = article
        Glide.with(itemView.context).load(article.urlToImage).into(imageTitle)
        title.text = article.title
        //if(article.author.isNullOrEmpty()) author.text = "Unknow" else author.text = article.author
        val date: String = FormatDate.toSimpleString(article.publishedAt)
        publicationDate.text = date


    }

    companion object {
        fun create(parent: ViewGroup): ArticleViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.article_view, parent, false)
            return ArticleViewHolder(view)
        }
    }

}