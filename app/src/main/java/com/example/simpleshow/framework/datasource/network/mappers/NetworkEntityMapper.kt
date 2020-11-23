package com.example.simpleshow.framework.datasource.network.mappers

import com.example.simpleshow.business.data.network.ApiResponse
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.ErrorEntity
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.datasource.network.model.OpenWeatherApiError
import com.example.simpleshow.framework.datasource.network.model.WeatherDataNetworkEntry
import javax.inject.Inject

class NetworkEntityMapper @Inject constructor() {

    private fun mapFromEntity(model: WeatherDataNetworkEntry, currentTime: Long): WeatherData {
        return WeatherData(
            model.cityId,
            model.cityName,
            model.weather.first().main,
            model.weather.first().description,
            model.main.temperature,
            model.main.temperatureFeelsLike,
            model.main.temperatureMin,
            model.main.temperatureMax,
            model.main.pressure,
            model.main.humidity,
            model.wind.windSpeed,
            model.wind.windDeg,
            model.clouds.cloundCoverage,
            model.sys.sunriseTime,
            model.sys.sunsetTime,
            model.weather.first().icon,
            currentTime
        )
    }

    fun mapFromApiResponse(
        apiResponse: ApiResponse<WeatherDataNetworkEntry, OpenWeatherApiError>,
        currentTime: Long
    ): Data<WeatherData> {
        return when (apiResponse) {
            is ApiResponse.Success -> {
                Data.Result(mapFromEntity(apiResponse.value, currentTime))
            }
            is ApiResponse.ApiError -> {
                Data.Error(ErrorEntity.Unknown, apiResponse.value.message)
            }
            is ApiResponse.NetworkError -> {
                Data.Error(ErrorEntity.Unknown, apiResponse.error.message)
            }
            is ApiResponse.GenericError -> {
                Data.Error(ErrorEntity.Unknown, apiResponse.error?.message)
            }
        }
    }
}


