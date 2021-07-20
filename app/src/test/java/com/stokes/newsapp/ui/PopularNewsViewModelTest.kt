package com.stokes.newsapp.ui

import com.stokes.newsapp.data.PopularNewsViewModel
import com.stokes.newsapp.data.repositories.FakePopularNewsRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PopularNewsViewModelTest {
    private lateinit var viewModel: PopularNewsViewModel

    @Before
    fun setup() {
        viewModel = PopularNewsViewModel(FakePopularNewsRepository())
    }

}