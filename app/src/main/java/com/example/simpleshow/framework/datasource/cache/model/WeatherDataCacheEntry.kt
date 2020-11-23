package com.example.simpleshow.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherDataCacheEntry(

    //id
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val cityId: Int,
    @ColumnInfo(name = "name") val cityName: String,
    @ColumnInfo(name = "main") val main: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "temp") val temperature: Double,
    @ColumnInfo(name = "feels_like") val temperatureFeelsLike: Double,
    @ColumnInfo(name = "temp_min") val temperatureMin: Double,
    @ColumnInfo(name = "temp_max") val temperatureMax: Double,
    @ColumnInfo(name = "pressure") val pressure: Int,
    @ColumnInfo(name = "humidity") val humidity: Int,
    @ColumnInfo(name = "speed") val windSpeed: Double,
    @ColumnInfo(name = "deg") val windDeg: Int,
    @ColumnInfo(name = "all") val cloudsCoverage: Int,
    @ColumnInfo(name = "sunrise") val sunriseTime: Long,
    @ColumnInfo(name = "sunset") val sunsetTime: Long,
    @ColumnInfo(name = "icon_url") val iconUrl: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: Long

)