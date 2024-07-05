package com.example.flickrimagesearch.data

import java.io.Serializable

data class FlickrItem(
    val title: String,
    val link: String,
    val media: FlickrMedia,
    val description: String,
    val author: String,
    val published: String,
    val tags: String
): Serializable
