package com.stokes.newsapp.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.stokes.newsapp.data.models.Media
import com.stokes.newsapp.data.models.MediaMetadata
import com.stokes.newsapp.data.models.PopularNewsResponse
import com.stokes.newsapp.data.models.Result
import com.stokes.newsapp.data.repositories.FakePopularNewsRepositoryTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PopularNewsViewModelTest1{val metas = listOf(
    MediaMetadata(
        url = "https://static01.nyt.com/images/2021/07/20/opinion/19renkl-dolly/19renkl-dolly-thumbStandard-v4.jpg",
        format = "Standard Thumbnail",
        height = 75,
        width = 75
    ),
    MediaMetadata(
        url = "https://static01.nyt.com/images/2021/07/20/opinion/19renkl-dolly/19renkl-dolly-thumbStandard-v4.jpg",
        format = "Standard Thumbnail",
        height = 75,
        width = 75
    ),
    MediaMetadata(
        url = "https://static01.nyt.com/images/2021/07/20/opinion/19renkl-dolly/19renkl-dolly-mediumThreeByTwo440-v3.jpg",
        format = "mediumThreeByTwo440",
        height = 293,
        width = 440
    )
)

    val media = listOf(
        Media(
            `media-metadata` = metas,
            type = "image",
            subtype = "photo",
            caption = "",
            copyright = "John Seakwood/Walt Disney Television, via Getty Images",
            approved_for_syndication = 1,
        )
    )

    var article: Result = Result(
        title = "Dolly Parton Tried. But Tennessee Is Squandering a Miracle.",
        byline = "By Margaret Renkl",
        published_date = "2021-07-19",
        media = media,
        uri = "nyt://article/aacca17c-23a6-5a89-be2c-dd78f8e51dae",
        url = "https://www.nytimes.com/2021/07/19/opinion/tennessee-dolly-parton-covid-vaccine.html",
        id = 100000007869975,
        asset_id = 100000007869975,
        source = "New York Times",
        updated = "2021-07-20 00:50:58",
        section = "Opinion",
        subsection = "",
        nytdsection = "opinion",
        adx_keywords = "Vaccination and Immunization;Coronavirus (2019-nCoV);Rural Areas;State Legislatures;Rumors and Misinformation;Parton, Dolly;Fiscus, Michelle;Tennessee",
        column = null,
        type = "Article",
        abstract = "I just don’t get it.",
        des_facet = listOf(
            "Vaccination and Immunization",
            "Coronavirus (2019-nCoV)",
            "Rural Areas",
            "State Legislatures",
            "Rumors and Misinformation"
        ),
        org_facet = listOf<String>(),
        per_facet = listOf(
            "Parton, Dolly",
            "Fiscus, Michelle"
        ),
        geo_facet = listOf<String>(),
        eta_id = 0
    )
    private lateinit var viewModel: PopularNewsViewModel

    private lateinit var fakePopularNewsRepositoryTest: FakePopularNewsRepositoryTest

    var context: Context = ApplicationProvider.getApplicationContext()
    @Before
    fun setup() {
        fakePopularNewsRepositoryTest.popularNewsResponse = PopularNewsResponse("Copyright (c) 2021 The New York Times Company.  All Rights Reserved.",
            20, listOf(article), "OK")

        viewModel = PopularNewsViewModel(context,fakePopularNewsRepositoryTest)
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