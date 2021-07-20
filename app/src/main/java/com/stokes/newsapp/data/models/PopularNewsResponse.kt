package com.stokes.newsapp.data.models

data class PopularNewsResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    var status: String
)