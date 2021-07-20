package com.stokes.newsapp.validators

object SearchValidator {
    fun validateSearchInput(searchText: String): Boolean{
        return searchText.isNotEmpty();
    }
}