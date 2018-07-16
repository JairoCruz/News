package com.example.tse.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.Article
import com.example.tse.news.ui.ArticleAdapter
import com.example.tse.news.ui.ListNewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListNewsViewModel
    private val adapter = ArticleAdapter()
    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
                .get(ListNewsViewModel::class.java)

        initAdapter()
        viewModel.articles

    }

    private fun initAdapter(){
        list.adapter = adapter
        viewModel.articles.observe(this, Observer<PagedList<Article>>{
            Log.e(TAG, "List: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networErrors.observe(this, Observer<String> {
            Toast.makeText(this, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
        })

    }

    private fun showEmptyList(show: Boolean){
        if(show){
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        }else{
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }


}
