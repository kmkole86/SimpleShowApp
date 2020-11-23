package com.example.simpleshow.business.data.cache

sealed class CacheResponse<out T> {

    data class Success<out T>(val result: T) : CacheResponse<T>()

    data class GenericError(val errorMessage: String? = null) : CacheResponse<Nothing>()
}