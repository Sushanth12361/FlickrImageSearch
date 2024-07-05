package com.example.flickrimagesearch.util.api

import com.example.flickrimagesearch.util.ApiResult
import retrofit2.Response

interface ApiUtil {
    fun <T> getResult(response: Response<T>): ApiResult<T>
    fun <T> parseError(exception: Exception): ApiResult<T>
}