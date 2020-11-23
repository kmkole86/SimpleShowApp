package com.example.simpleshow.business.data.network.abstraction

import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData

interface WeatherNetworkDataSource {

    suspend fun fetchWeatherData(): Data<WeatherData>
}