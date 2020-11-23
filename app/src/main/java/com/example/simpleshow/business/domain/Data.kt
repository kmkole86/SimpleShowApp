package com.example.simpleshow.business.domain

import com.example.simpleshow.business.domain.model.ErrorEntity

sealed class Data<out R : Any> {

    data class Result<out R : Any>(val data: R) : Data<R>()

    data class Error(val errorType: ErrorEntity, val message: String? = null) :
        Data<Nothing>()
}