package com.stokes.newsapp.data.api

import com.stokes.newsapp.BuildConfig
import com.stokes.newsapp.data.models.PopularNewsResponse
import com.stokes.newsapp.util.Constants.LAST_30_DAYS_NEWS
import com.stokes.newsapp.util.Constants.LAST_7_DAYS_NEWS
import com.stokes.newsapp.util.Constants.TODAYS_NEWS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularNewsApi {

    @GET("$TODAYS_NEWS.json")
    suspend fun getTodaysNews(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ) : Response<PopularNewsResponse>

    @GET("$LAST_7_DAYS_NEWS.json")
    suspend fun getLast7DaysNews(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ) : Response<PopularNewsResponse>

    @GET("$LAST_30_DAYS_NEWS.json")
    suspend fun getLast30DaysNews(
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ) : Response<PopularNewsResponse>
}