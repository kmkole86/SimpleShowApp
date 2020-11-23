package com.example.simpleshow.framework.datasource.cache.mappers

import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.business.domain.util.EntityMapper
import com.example.simpleshow.framework.datasource.cache.model.WeatherDataCacheEntry
import javax.inject.Inject

class CacheEntityMapper @Inject constructor() : EntityMapper<WeatherDataCacheEntry, WeatherData> {

    override fun mapFromEntity(model: WeatherDataCacheEntry): WeatherData {
        return WeatherData(
            model.cityId,
            model.cityName,
            model.main,
            model.description,
            model.temperature,
            model.temperatureFeelsLike,
            model.temperatureMin,
            model.temperatureMax,
            model.pressure,
            model.humidity,
            model.windSpeed,
            model.windDeg,
            model.cloudsCoverage,
            model.sunriseTime,
            model.sunsetTime,
            model.iconUrl,
            model.updatedAt
        )
    }

    override fun mapToEntity(model: WeatherData): WeatherDataCacheEntry {
        return WeatherDataCacheEntry(
            model.cityId,
            model.cityName,
            model.main,
            model.description,
            model.temperature,
            model.temperatureFeelsLike,
            model.temperatureMin,
            model.temperatureMax,
            model.pressure,
            model.humidity,
            model.windSpeed,
            model.windDeg,
            model.cloudsCoverage,
            model.sunriseTime,
            model.sunsetTime,
            model.iconUrl,
            model.updatedAt
        )
    }
}