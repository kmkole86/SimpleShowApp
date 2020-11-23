package com.example.simpleshow.framework.datasource.network.abstraction

import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData

interface WeatherNetworkService {

    suspend fun fetchWeatherData(): Data<WeatherData>
}