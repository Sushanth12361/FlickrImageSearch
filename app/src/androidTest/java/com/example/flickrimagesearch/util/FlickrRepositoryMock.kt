package com.example.flickrimagesearch.util

import com.example.flickrimagesearch.data.FlickrResponse
import com.example.flickrimagesearch.repository.FlickrRepository

class FlickrRepositoryMock: FlickrRepository {
    var response: ApiResult<FlickrResponse> = ApiResult.Success(Mocks.flickrResponse)
    override suspend fun getFeeds(search: String): ApiResult<FlickrResponse> {
        return response
    }

    fun changeResponseToSuccess(){
        response = ApiResult.Success(Mocks.flickrResponse)
    }

    fun changeResponseToError(){
        response = ApiResult.Error("Error")
    }

}