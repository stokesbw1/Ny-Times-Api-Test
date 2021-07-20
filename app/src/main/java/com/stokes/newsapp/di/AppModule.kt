package com.stokes.newsapp.di

import com.stokes.newsapp.data.api.PopularNewsApi
import com.stokes.newsapp.data.repositories.DefaultPopularNewsRepository
import com.stokes.newsapp.data.repositories.PopularNewsRepository
import com.stokes.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providePopularNewsApi(): PopularNewsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(PopularNewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultPopularNewsRepository (
        api: PopularNewsApi
    ) = DefaultPopularNewsRepository(api) as PopularNewsRepository
}