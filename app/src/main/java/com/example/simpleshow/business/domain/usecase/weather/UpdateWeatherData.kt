package com.example.simpleshow.business.domain.usecase.weather

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.data.network.abstraction.WeatherNetworkDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.Reducer
import com.example.simpleshow.framework.presentation.weather.WeatherViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdateWeatherData @Inject constructor(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val weatherCacheDataSource: WeatherCacheDataSource
) {
    operator fun invoke() = getUpdateWeatherData()

    private fun getUpdateWeatherData(): Flow<Reducer<WeatherViewState>> =
        flow<Reducer<WeatherViewState>> {

            when (val result = weatherNetworkDataSource.fetchWeatherData()) {
                is Data.Result -> {
                    emit(Reducer { copy(weatherData = result.data, isLoading = false) })
                    weatherCacheDataSource.insertWeatherData(result.data)
                }
                is Data.Error -> {
                    emit(Reducer {
                        copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = result.message
                        )
                    })
                }
                else -> {
                }
            }
        }.onStart { emit(Reducer { copy(isLoading = true) }) }
}