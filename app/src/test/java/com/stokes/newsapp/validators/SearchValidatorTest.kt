package com.stokes.newsapp.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchValidatorTest {

    @Test
    fun `search input empty returns false`() {
        var searchInput = "";
        var result = SearchValidator.validateSearchInput(searchInput)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun `search input not empty returns true`() {
        var searchInput = "searchInput";
        var result = SearchValidator.validateSearchInput(searchInput)
        assertThat(result).isEqualTo(true)
    }
}