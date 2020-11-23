package com.example.simpleshow.framework.datasource.network.model

import com.google.gson.annotations.SerializedName

data class Clouds(

    @SerializedName("all") val cloundCoverage: Int
)