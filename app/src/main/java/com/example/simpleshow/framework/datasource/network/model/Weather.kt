package com.example.simpleshow.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class Weather(

    @SerializedName("id") val weather_id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)