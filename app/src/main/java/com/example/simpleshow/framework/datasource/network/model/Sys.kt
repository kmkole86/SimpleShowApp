package com.example.simpleshow.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class Sys(

    @SerializedName("type") val type: Int,
    @SerializedName("id") val sys_id: Int,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long
)