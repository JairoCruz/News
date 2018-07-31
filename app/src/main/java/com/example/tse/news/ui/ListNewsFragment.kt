package com.example.tse.news.ui


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.tse.news.Injection

import com.example.tse.news.R
import com.example.tse.news.model.Article
import com.example.tse.news.model.Source
import kotlinx.android.synthetic.main.fragment_list_news.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListNewsFragment : Fragment() {

    private lateinit var viewModel: ListNewsViewModel
    private lateinit var viewModelSource: ListSourcesNewsViewModel
    private val adapter = ArticleAdapter()
    private val TAG: String = ListNewsFragment::class.java.simpleName


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(view!!.context))
                .get(ListNewsViewModel::class.java)

        viewModelSource = ViewModelProviders.of(this, Injection.provideViewModelFactorySourcesNews(view!!.context))
                .get(ListSourcesNewsViewModel::class.java)



        viewModelSource.sources.observe(this, Observer<List<Source>>{
            Log.e(TAG, "Sources: ${it?.size}")
        })

        viewModelSource.networErrors.observe(this, Observer<String> {
            Toast.makeText(view?.context, "Woooss ${it}", Toast.LENGTH_LONG).show()
        })

        initAdapter()
        viewModel.articles
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_news, container, false)

    }


    private fun initAdapter() {
        list.adapter = adapter
        viewModel.articles.observe(this, Observer<PagedList<Article>>{
            Log.e(TAG, "List: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networErrors.observe(this, Observer<String> {
            Toast.makeText(view?.context, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
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


    private fun setUpScrollListener(){
        val layoutManager = list.layoutManager as LinearLayoutManager

    }


}
