package com.example.tse.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tse.news.api.NewsService
import com.example.tse.news.api.searchNews
import com.example.tse.news.model.Article
import com.example.tse.news.ui.ArticleAdapter
import com.example.tse.news.ui.ListNewsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
