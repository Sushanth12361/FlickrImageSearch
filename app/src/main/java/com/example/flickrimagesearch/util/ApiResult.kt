package com.example.flickrimagesearch.util

sealed class ApiResult<out T> {
    class Error(val error: String?) : ApiResult<Nothing>()
    class Success<T>(val result: T?) : ApiResult<T>()
}