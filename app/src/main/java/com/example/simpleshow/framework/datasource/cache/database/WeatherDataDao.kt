package com.example.simpleshow.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpleshow.framework.datasource.cache.model.WeatherDataCacheEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherDataCacheEntry: WeatherDataCacheEntry)

    @Query("DELETE FROM WEATHER_DATA")
    suspend fun deleteWeatherData()

    @Query("SELECT * FROM weather_data")
    suspend fun getWeatherData(): WeatherDataCacheEntry

    @Query("SELECT * FROM weather_data")
    fun getObservableWeatherData(): Flow<WeatherDataCacheEntry>
}