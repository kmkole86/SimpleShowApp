package com.example.simpleshow.business.data.network.implementation

import com.example.simpleshow.business.data.network.abstraction.WeatherNetworkDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.datasource.network.abstraction.WeatherNetworkService
import javax.inject.Inject

class WeatherNetworkDataSourceImpl @Inject constructor(private val weatherNetworkService: WeatherNetworkService) :
    WeatherNetworkDataSource {

    override suspend fun fetchWeatherData(): Data<WeatherData> {
        return weatherNetworkService.fetchWeatherData()
    }
}