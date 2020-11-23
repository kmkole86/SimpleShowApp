package com.example.simpleshow.framework.presentation.splash

import com.example.simpleshow.business.domain.model.WeatherData

//view state should be immutable, so data class is best candidate since you can only make copy
data class SplashViewState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val navigateToWeather: Boolean = false
)