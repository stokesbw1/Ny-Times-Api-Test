package com.stokes.newsapp.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.stokes.newsapp.data.repositories.FakePopularNewsRepository

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PopularNewsViewModelTest1{
    private lateinit var viewModel: PopularNewsViewModel

    var context: Context = ApplicationProvider.getApplicationContext()
    @Before
    fun setup() {
        viewModel = PopularNewsViewModel(context,FakePopularNewsRepository())
    }

    @Test
    fun searchQueryEmptyReturnsFalse() {
        var query = "";
        var result = viewModel.searchForArticles(query)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun searchQueryNotEmptyReturnsFalse() {
        var query = "query";
        var result = viewModel.searchForArticles(query)
        Truth.assertThat(result).isTrue()
    }
}