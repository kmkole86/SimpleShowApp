package com.example.simpleshow.business.domain.model

sealed class ErrorEntity {

    object Network : ErrorEntity()

    object Timeout : ErrorEntity()

    object NotFound : ErrorEntity()

    object AccessDenied : ErrorEntity()

    object ServiceUnavailable : ErrorEntity()

    object Unknown : ErrorEntity()
}