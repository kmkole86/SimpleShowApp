package com.example.simpleshow.business.data.cache.implementation

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.datasource.cache.abstraction.WeatherDaoService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherCacheDataSourceImpl @Inject constructor(
    private val weatherDaoService: WeatherDaoService
) :
    WeatherCacheDataSource {

    override suspend fun insertWeatherData(weatherData: WeatherData) =
        weatherDaoService.insertWeatherData(weatherData)

    override suspend fun getWeatherData(): Data<WeatherData> =
        weatherDaoService.getWeatherData()

    override fun getObservableWeatherData(): Flow<Data<WeatherData>> =
        weatherDaoService.getObservableWeatherData()

    override suspend fun deleteWeatherData() = weatherDaoService.deleteWeatherData()
}