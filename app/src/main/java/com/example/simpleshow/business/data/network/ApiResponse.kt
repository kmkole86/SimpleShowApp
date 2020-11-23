package com.example.simpleshow.business.data.network

import java.io.IOException

sealed class ApiResponse<out R : Any, out E : Any> {

    data class Success<out R : Any>(val value: R) : ApiResponse<R, Nothing>()

    //api failure (has body and err code)
    data class ApiError<E : Any>(val value: E, val code: Int? = null) : ApiResponse<Nothing, E>()

    //network error like no internet
    data class NetworkError(val error: IOException) : ApiResponse<Nothing, Nothing>()

    //everything else that is not api or network error
    data class GenericError(val error: Throwable?) : ApiResponse<Nothing, Nothing>()
}