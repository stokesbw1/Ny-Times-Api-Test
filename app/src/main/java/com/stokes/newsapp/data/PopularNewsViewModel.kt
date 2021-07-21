package com.stokes.newsapp.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ApplicationProvider
import com.stokes.newsapp.data.models.PopularNewsResponse
import com.stokes.newsapp.data.models.Result
import com.stokes.newsapp.data.repositories.PopularNewsRepository
import com.stokes.newsapp.util.Constants.NEWS_TIME_DELAY
import com.stokes.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PopularNewsViewModel @Inject constructor(
    private var popularNewsRepository: PopularNewsRepository
) : ViewModel() {


    fun searchForArticles(query: String): Boolean{
        if(query.isNotEmpty()) {
            //Toast.makeText(context, "Looking for $query", Toast.LENGTH_LONG).show()
            return true
        }

        return false
    }

    private var popularNewsData: MutableLiveData<Resource<PopularNewsResponse>> = MutableLiveData()
    fun getPopularNewsData(): LiveData<Resource<PopularNewsResponse>> {
        return popularNewsData
    }

    init {
        getLast7DaysPopularNews()
    }

    fun getTodaysPopularNews() = viewModelScope.launch {
        popularNewsData.postValue(Resource.loading(null))
        val response = popularNewsRepository.getTodaysNews()
        popularNewsData.postValue(response)
    }

    private fun getLast7DaysPopularNews() = viewModelScope.launch {
        popularNewsData.postValue(Resource.loading(null))
        val response = popularNewsRepository.getLast7DaysNews()
        popularNewsData.postValue(response)
    }

    fun getLast30DaysPopularNews() = viewModelScope.launch {
        popularNewsData.postValue(Resource.loading(null))
        val response = popularNewsRepository.getLast30DaysNews()
        popularNewsData.postValue(response)
    }
}