package com.stokes.newsapp.data.repositories

import com.google.common.truth.Truth
import com.stokes.newsapp.util.Constants.LAST_30_DAYS_NEWS
import com.stokes.newsapp.util.Constants.LAST_7_DAYS_NEWS
import com.stokes.newsapp.util.Constants.TODAYS_NEWS
import com.stokes.newsapp.validators.SearchValidator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class DefaultPopularNewsRepositoryTest {

    @Test
    fun `Today's news constant value equal to 1 returns true`() {
        Truth.assertThat(TODAYS_NEWS).isEqualTo(1)
    }

    @Test
    fun `Last seven days news constant value equal to 7 returns true`() {
        Truth.assertThat(LAST_7_DAYS_NEWS).isEqualTo(7)
    }

    @Test
    fun `Last thirty days news constant value equal to 30 returns true`() {
        Truth.assertThat(LAST_30_DAYS_NEWS).isEqualTo(30)
    }
}