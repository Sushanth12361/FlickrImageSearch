package com.example.flickrimagesearch.mocks

import com.example.flickrimagesearch.data.FlickrItem
import com.example.flickrimagesearch.data.FlickrMedia
import com.example.flickrimagesearch.data.FlickrResponse

object Mocks {
    val flickrItems = listOf(
        FlickrItem(
        title = "",
        link = "",
        media = FlickrMedia(url = ""),
        description = "",
        author = "",
        published = "",
        tags = "",
    )
    )
    val flickrResponse = FlickrResponse(title = "", link = "", items = flickrItems)
}