package com.stokes.newsapp.data.models

import java.io.Serializable

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
): Serializable