package com.example.simpleshow.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpleshow.framework.datasource.cache.model.WeatherDataCacheEntry

@Database(entities = [WeatherDataCacheEntry::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDataDao(): WeatherDataDao

    companion object {
        val DATABASE_NAME: String = "weather_db"
    }
}