package com.example.simpleshow.business.domain.usecase.splash

import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.data.network.abstraction.WeatherNetworkDataSource
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.framework.presentation.splash.SplashViewState
import com.example.simpleshow.util.Constants
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

    private fun getInitData(): Flow<SplashViewState> = flow<SplashViewState> {
        emit(SplashViewState.Loading)
        when (
            val result = weatherNetworkDataSource.fetchWeatherData(
                Constants.DEFAULT_CITY,
                Constants.DEFAULT_UNIT
            )) {
            is Data.Result -> {
                weatherCacheDataSource.insertWeatherData(result.data)
                emit(SplashViewState.NavigateToWeatherPage)
            }
            is Data.Error -> {
                emit(SplashViewState.Error(result.message))
            }
        }
    }.flowOn(dispatcher)
}