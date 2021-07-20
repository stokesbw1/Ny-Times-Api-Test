package com.stokes.newsapp.data.repositories

import com.stokes.newsapp.data.models.PopularNewsResponse
import com.stokes.newsapp.data.models.Result
import com.stokes.newsapp.util.Constants.LAST_30_DAYS_NEWS
import com.stokes.newsapp.util.Constants.LAST_7_DAYS_NEWS
import com.stokes.newsapp.util.Constants.TODAYS_NEWS
import com.stokes.newsapp.util.Resource

class FakePopularNewsRepository: PopularNewsRepository {
    private var popularNews = mutableListOf<Result>()
    private var popularNewsResponse: PopularNewsResponse = PopularNewsResponse("Copyright (c) 2021 The New York Times Company.  All Rights Reserved.",
        20, popularNews, "OK")

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getTodaysNews(apiKey: String): Resource<PopularNewsResponse> {
        return getNews(TODAYS_NEWS)
    }

    override suspend fun getLast7DaysNews(apiKey: String): Resource<PopularNewsResponse> {
        return getNews(LAST_7_DAYS_NEWS)
    }

    override suspend fun getLast30DaysNews(apiKey: String): Resource<PopularNewsResponse> {
        return getNews(LAST_30_DAYS_NEWS)
    }

    suspend fun getNews(period: Int): Resource<PopularNewsResponse> {
        if(shouldReturnNetworkError) {
            return Resource.error("Couldn't reach the server. Check your internet connection", null)
        }

        return Resource.success(popularNewsResponse)
    }
}