package com.example.flickrimagesearch.api

import com.example.flickrimagesearch.data.FlickrResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    /**
     * Get Flicker data from the API
     */
    @GET("photos_public.gne")
    suspend fun getFeeds(
        @Query(value = "format") page: String = "json",
        @Query(value = "nojsoncallback") noJsonCallback: Int = 1,
        @Query(value = "tags") search: String
    ): Response<FlickrResponse>
}