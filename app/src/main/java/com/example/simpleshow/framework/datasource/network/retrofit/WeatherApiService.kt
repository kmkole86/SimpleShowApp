package com.example.simpleshow.framework.datasource.network.retrofit

import com.example.simpleshow.business.data.network.ApiResponse
import com.example.simpleshow.framework.datasource.network.model.OpenWeatherApiError
import com.example.simpleshow.framework.datasource.network.model.WeatherDataNetworkEntry
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun fetchWeatherData(
        @Query("q") city: String,
        @Query("units") units: String
    ): ApiResponse<WeatherDataNetworkEntry, OpenWeatherApiError>
}