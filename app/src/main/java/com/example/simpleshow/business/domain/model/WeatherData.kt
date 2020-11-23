package com.example.simpleshow.business.domain.model

data class WeatherData(
    val cityId: Int,
    val cityName: String,
    val main: String,
    val description: String,
    val temperature: Double,
    val temperatureFeelsLike: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDeg: Int,
    val cloudsCoverage: Int,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val iconUrl: String,
    val updatedAt: Long
)