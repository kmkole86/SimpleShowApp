package com.example.simpleshow.framework.presentation.weather

import com.example.simpleshow.business.domain.model.WeatherData

//view state should be immutable, so data class is best candidate since you can only make copy
data class WeatherViewState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)
