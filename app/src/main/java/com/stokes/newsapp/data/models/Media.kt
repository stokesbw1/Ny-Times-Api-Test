package com.stokes.newsapp.data.models

import java.io.Serializable

data class Media(
    val approved_for_syndication: Int?,
    val caption: String?,
    val copyright: String?,
    val `media-metadata`: List<MediaMetadata>?,
    val subtype: String?,
    val type: String?
): Serializable