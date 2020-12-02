package com.example.simpleshow.framework.datasource.network.implementation

import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.datasource.network.abstraction.WeatherNetworkService
import com.example.simpleshow.framework.datasource.network.mappers.NetworkEntityMapper
import com.example.simpleshow.framework.datasource.network.retrofit.WeatherApiService
import javax.inject.Inject

class WeatherNetworkServiceImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val mapper: NetworkEntityMapper
) : WeatherNetworkService {

    override suspend fun fetchWeatherData(city: String, units: String): Data<WeatherData> {
        return mapper.mapFromApiResponse(
            weatherApiService.fetchWeatherData(city, units),
            System.currentTimeMillis()
        )
    }
}