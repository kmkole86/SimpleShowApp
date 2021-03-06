package com.example.simpleshow.business.data.network.abstraction

import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.util.MeasurementUnit

interface WeatherNetworkDataSource {

    suspend fun fetchWeatherData(city: String, unit: MeasurementUnit): Data<WeatherData>
}