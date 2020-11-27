package com.example.simpleshow.business.domain.usecase.weather

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.presentation.weather.WeatherViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ShowWeatherData @Inject constructor(
    private val weatherCacheDataSource: WeatherCacheDataSource
) {
    operator fun invoke() = getShowWeatherData()

    private fun getShowWeatherData(): Flow<WeatherViewState> =
        weatherCacheDataSource.getObservableWeatherData()
            .mapToStateHandlingErrors()
            .onStart { emit(WeatherViewState.Loading) }

    private fun Flow<Data<WeatherData>>.mapToStateHandlingErrors(): Flow<WeatherViewState> =
        map {
            when (it) {
                is Data.Result -> WeatherViewState.Data(it.data)
                is Data.Error -> WeatherViewState.Error(it.message)
            }
        }.catch { e -> emit(WeatherViewState.Error(e.message)) }
}