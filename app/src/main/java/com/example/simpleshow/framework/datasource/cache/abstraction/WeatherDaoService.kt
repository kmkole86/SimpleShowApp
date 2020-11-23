package com.example.simpleshow.framework.datasource.cache.abstraction

import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherDaoService {

    suspend fun insertWeatherData(weatherData: WeatherData)

    suspend fun getWeatherData(): Data<WeatherData>

    fun getObservableWeatherData(): Flow<Data<WeatherData>>

    suspend fun deleteWeatherData()
}