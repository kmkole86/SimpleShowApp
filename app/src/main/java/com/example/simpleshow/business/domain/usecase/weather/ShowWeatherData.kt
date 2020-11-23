package com.example.simpleshow.business.domain.usecase.weather

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.Reducer
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.presentation.weather.WeatherViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ShowWeatherData @Inject constructor(
    private val weatherCacheDataSource: WeatherCacheDataSource
) {
    operator fun invoke() = getShowWeatherData()

    private fun getShowWeatherData(): Flow<Reducer<WeatherViewState>> =
        weatherCacheDataSource.getObservableWeatherData()
            .mapToReducerHandlingErrors()
            .onStart { Reducer<WeatherViewState> { copy(isLoading = true) } }

    private fun Flow<Data<WeatherData>>.mapToReducerHandlingErrors(): Flow<Reducer<WeatherViewState>> =
        map {
            when (it) {
                is Data.Result -> Reducer<WeatherViewState> {
                    copy(
                        weatherData = it.data,
                        isLoading = false
                    )
                }
                is Data.Error -> Reducer<WeatherViewState> {
                    copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = it.message
                    )
                }
            }
        }.catch { e ->
            emit(Reducer {
                copy(
                    isError = true,
                    errorMessage = e.message
                )
            })
        }
}