package com.example.simpleshow.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class Wind(

    @SerializedName("speed") val windSpeed: Double,
    @SerializedName("deg") val windDeg: Int
)