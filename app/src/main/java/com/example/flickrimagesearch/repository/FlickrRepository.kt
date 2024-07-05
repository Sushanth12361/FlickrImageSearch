package com.example.flickrimagesearch.repository

import com.example.flickrimagesearch.data.FlickrResponse
import com.example.flickrimagesearch.util.ApiResult

interface FlickrRepository {
    suspend fun getFeeds(search: String): ApiResult<FlickrResponse>
}