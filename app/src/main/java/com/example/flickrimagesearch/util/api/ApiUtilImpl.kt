package com.example.flickrimagesearch.util.api

import com.example.flickrimagesearch.util.ApiResult
import retrofit2.Response
import java.io.IOException

class ApiUtilImpl : ApiUtil {
    override fun <T> getResult(response: Response<T>): ApiResult<T> {
        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            return ApiResult.Error(errorBody)
        }

        response.body()?.let {
            return ApiResult.Success(it)
        }?: kotlin.run {
            return ApiResult.Error("Unable to get data from the api")
        }
    }

    override fun <T> parseError(exception: Exception): ApiResult<T> {
        return if (exception is IOException)
            ApiResult.Error("Occurred a problem with the connection, please try again!")
        else ApiResult.Error(exception.message)
    }
}