package com.example.simpleshow.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class Coord(

    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)