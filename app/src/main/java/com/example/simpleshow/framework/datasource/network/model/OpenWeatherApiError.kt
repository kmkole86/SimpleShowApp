package com.example.simpleshow.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class OpenWeatherApiError(
    @SerializedName("cod") val code: Int? = null,
    @SerializedName("message") val message: String? = null
)