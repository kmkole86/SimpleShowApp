package com.example.simpleshow.business.domain.usecase.splash

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.data.network.abstraction.WeatherNetworkDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.Reducer
import com.example.simpleshow.framework.presentation.splash.SplashViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchWeatherData @Inject constructor(
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val weatherCacheDataSource: WeatherCacheDataSource,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke() = getInitData()

    private fun getInitData(): Flow<Reducer<SplashViewState>> = flow<Reducer<SplashViewState>> {
        emit(Reducer { copy(isLoading = true) })
        when (val result = weatherNetworkDataSource.fetchWeatherData()) {
            is Data.Result -> {
                weatherCacheDataSource.insertWeatherData(result.data)
                emit(Reducer {
                    copy(
                        weatherData = result.data,
                        isLoading = false,
                        navigateToWeather = true
                    )
                })
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
    }.flowOn(dispatcher)
}