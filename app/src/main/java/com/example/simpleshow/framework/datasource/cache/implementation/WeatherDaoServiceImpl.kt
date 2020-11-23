package com.example.simpleshow.framework.datasource.cache.implementation


import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.ErrorEntity
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.datasource.cache.abstraction.WeatherDaoService
import com.example.simpleshow.framework.datasource.cache.database.WeatherDataDao
import com.example.simpleshow.framework.datasource.cache.mappers.CacheEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WeatherDaoServiceImpl @Inject constructor(
    private val weatherDataDao: WeatherDataDao,
    private val mapper: CacheEntityMapper,
    private val dispatcher: CoroutineDispatcher
) : WeatherDaoService {

    override suspend fun insertWeatherData(weatherData: WeatherData) {
        weatherDataDao.insertWeatherData(mapper.mapToEntity(weatherData))
    }

    override suspend fun deleteWeatherData() {
        weatherDataDao.deleteWeatherData()
    }

    override suspend fun getWeatherData(): Data<WeatherData> {
        return weatherDataDao.getWeatherData()
            .let { Data.Result(mapper.mapFromEntity(it)) }
    }

    override fun getObservableWeatherData(): Flow<Data<WeatherData>> {
        return weatherDataDao.getObservableWeatherData().distinctUntilChanged()
            .map { Data.Result(mapper.mapFromEntity(it)) }
            .catch { error -> Data.Error(ErrorEntity.Unknown, error.message) }
            .flowOn(dispatcher)
    }
}