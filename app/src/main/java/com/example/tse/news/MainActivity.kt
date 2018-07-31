package com.example.tse.news

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.getSourcesNews
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.Article
import com.example.tse.news.ui.ArticleAdapter
import com.example.tse.news.ui.ListNewsViewModel
import com.example.tse.news.ui.ListSourcesNewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val API_KEY = "94294f4227bf4600849e1697d6a48ec1"
    private val TAG: String = MainActivity::class.java.simpleName



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        getSourcesNews(NewsService.create(), API_KEY, {
//            source -> run{ for (s in source){
//            Log.e(TAG,"VALOR:  ${s.name}")
//        }}
//        }, {
//            error -> Log.e(TAG, "error: ${error}")
//        })


        // Set up Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        setUpActionBar(navController)

        
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null, Navigation.findNavController(this, R.id.nav_host_fragment))
    }


    private fun setUpActionBar(navController: NavController){
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

}
