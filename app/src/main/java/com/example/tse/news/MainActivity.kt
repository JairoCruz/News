package com.example.tse.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
//        searchNews(NewsService.create(), COUNTRY, API_KEY, {
//            article ->  run{ for (art in article){
//            Log.e("MainActivity", "valor ${art.author}")
//        }}
//        },{
//            error -> Log.e("MainActivity", "error")
//        })

        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
                .get(ListNewsViewModel::class.java)

        //var decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        //list.addItemDecoration(decoration)
       // list.layoutManager = LinearLayoutManager(this)
       setUpScrollListener()

        initAdapter()
        viewModel.articles

    }

    private fun initAdapter(){
        list.adapter = adapter
        viewModel.articles.observe(this, Observer<List<Article>>{
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
    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    private fun setUpScrollListener(){
        val listLayoutManager = list.layoutManager as LinearLayoutManager
        // list name of RecyclerView
        list.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = listLayoutManager.itemCount
                val visibleItemCount = listLayoutManager.childCount
                val lastVisibleItem = listLayoutManager.findLastVisibleItemPosition()
                Log.e(TAG, "Total Item Count: $totalItemCount, Visible Item Count: $visibleItemCount, Last Item Visible: $lastVisibleItem")
//                if (lastVisibleItem == totalItemCount - 1){
//                    Log.e(TAG, "PIDO")
//                }
               viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
    }

}
