package com.example.simpleshow.business.domain.usecase.weather

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.data.network.abstraction.WeatherNetworkDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.framework.presentation.weather.WeatherViewState
import com.example.simpleshow.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdateWeatherData @Inject constructor(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val weatherCacheDataSource: WeatherCacheDataSource
) {
    operator fun invoke() = getUpdateWeatherData()

    private fun getUpdateWeatherData(): Flow<WeatherViewState> =
        flow<WeatherViewState> {

            when (val result = weatherNetworkDataSource.fetchWeatherData(
                Constants.DEFAULT_CITY,
                Constants.DEFAULT_UNIT
            )) {
                is Data.Result -> {
                    emit(WeatherViewState.Data(result.data))
                    weatherCacheDataSource.insertWeatherData(result.data)
                }
                is Data.Error -> WeatherViewState.Error(result.message)
            }
        }.onStart { emit(WeatherViewState.Loading) }
}