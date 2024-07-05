package com.example.flickrimagesearch.repository

import com.example.flickrimagesearch.api.FlickrApi
import com.example.flickrimagesearch.data.FlickrResponse
import com.example.flickrimagesearch.util.ApiResult
import com.example.flickrimagesearch.util.api.ApiUtil
import retrofit2.Response

class FlickrRepositoryImpl(
    private val flickrApi: FlickrApi,
    private val apiUtil: ApiUtil
) : FlickrRepository {
    override suspend fun getFeeds(search: String): ApiResult<FlickrResponse> {
        return try {
            val response: Response<FlickrResponse> = flickrApi.getFeeds(search = search)
            apiUtil.getResult(response)
        } catch (exception: Exception) {
            apiUtil.parseError(exception)
        }
    }
}