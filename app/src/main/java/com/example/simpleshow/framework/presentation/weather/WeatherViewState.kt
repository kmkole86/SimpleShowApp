package com.example.simpleshow.framework.presentation.weather

import com.example.simpleshow.business.domain.model.WeatherData

sealed class WeatherViewState {

    object Idle : WeatherViewState()

    object Loading : WeatherViewState()

    data class Data(val weatherData: WeatherData) : WeatherViewState()

    data class Error(val errorMessage: String? = null) : WeatherViewState()
}
