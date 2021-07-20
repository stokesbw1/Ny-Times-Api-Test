package com.stokes.newsapp.data.repositories

import com.stokes.newsapp.data.api.PopularNewsApi
import com.stokes.newsapp.data.models.PopularNewsResponse
import com.stokes.newsapp.util.Constants.LAST_30_DAYS_NEWS
import com.stokes.newsapp.util.Constants.LAST_7_DAYS_NEWS
import com.stokes.newsapp.util.Constants.TODAYS_NEWS
import com.stokes.newsapp.util.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultPopularNewsRepository @Inject constructor(
    private val popularNewsApi: PopularNewsApi
) : PopularNewsRepository {
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
        return try {
            when(period) {
                TODAYS_NEWS -> {
                    val response = popularNewsApi.getTodaysNews()
                    if(response.isSuccessful) {
                        response.body()?.let {
                            return@let Resource.success(it)
                        } ?: Resource.error("An Unknown Error Occurred", null)
                    }
                    else {
                        Resource.error("An Unknown Error Occurred", null)
                    }
                }

                LAST_30_DAYS_NEWS -> {
                    val response = popularNewsApi.getLast30DaysNews()
                    if(response.isSuccessful) {
                        response.body()?.let {
                            return@let Resource.success(it)
                        } ?: Resource.error("An Unknown Error Occurred", null)
                    }
                    else {
                        Resource.error("An Unknown Error Occurred", null)
                    }
                }

                else -> {
                val response = popularNewsApi.getLast7DaysNews()
                if(response.isSuccessful) {
                    response.body()?.let {
                        return@let Resource.success(it)
                    } ?: Resource.error("An Unknown Error Occurred", null)
                }
                else {
                    Resource.error("An Unknown Error Occurred", null)
                }
            }
            }
        }
        catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }

    }
}