package com.example.flickrimagesearch.mocks

import com.example.flickrimagesearch.data.FlickrResponse
import com.example.flickrimagesearch.repository.FlickrRepository
import com.example.flickrimagesearch.util.ApiResult

class FlickerRepositoryMock: FlickrRepository {
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