package com.stokes.newsapp.data.repositories

import com.stokes.newsapp.BuildConfig
import com.stokes.newsapp.data.models.PopularNewsResponse
import com.stokes.newsapp.util.Resource

interface PopularNewsRepository {
    suspend fun getTodaysNews(apiKey: String = BuildConfig.API_KEY): Resource<PopularNewsResponse>

    suspend fun getLast7DaysNews(apiKey: String = BuildConfig.API_KEY): Resource<PopularNewsResponse>

    suspend fun getLast30DaysNews( apiKey: String = BuildConfig.API_KEY) : Resource<PopularNewsResponse>
}