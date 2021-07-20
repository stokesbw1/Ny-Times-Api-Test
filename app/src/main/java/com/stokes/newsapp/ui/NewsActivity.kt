package com.stokes.newsapp.ui

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.activity.viewModels
import com.stokes.newsapp.R
import com.stokes.newsapp.data.PopularNewsViewModel
import com.stokes.newsapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

     private val viewModel: PopularNewsViewModel by viewModels()
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem: MenuItem? = menu?.findItem(R.id.nav_search)
        if (searchItem != null) {
            setSearchView(searchItem)
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun setSearchView(searchItem: MenuItem) {

        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search Popular News"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                query?.let {
                    viewModel.searchForArticles(query) }
                return false
            }


            override fun onQueryTextChange(query: String?): Boolean {
                job?.cancel()
                job = MainScope().launch {
                    delay(Constants.NEWS_TIME_DELAY)
                    query?.let {
                        viewModel.searchForArticles(query)
                    }
                }
                return true
            }
        })
    }
}