package com.example.simpleshow

import com.example.simpleshow.business.data.network.ApiResponse
import com.example.simpleshow.business.domain.Data
import com.example.simpleshow.business.domain.model.WeatherData
import com.example.simpleshow.framework.datasource.network.mappers.NetworkEntityMapper
import com.example.simpleshow.framework.datasource.network.model.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * CAUTION
 * learning
 * ENTER AT YOUR OWN RISK (:
 */

private const val FAKE_CURRENT_TIME: Long = 55
private const val DELTA: Double = 0.1
private const val UNAUTHORISED_ERROR_CODE: Int = 401
private const val UNAUTHORISED_ERROR_MESSAGE: String =
    "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."

class NetworkEntityMapperTest {

    @Test
    fun test_mapping_ApiResponseSuccess_to_WeatherData() {
        val mapper = NetworkEntityMapper()
        val inputData = createApiData()
        val successApiResponse = ApiResponse.Success<WeatherDataNetworkEntry>(inputData)

        val output: Data<WeatherData> =
            mapper.mapFromApiResponse(successApiResponse, FAKE_CURRENT_TIME)

        assertTrue(output is Data.Result<WeatherData>)
        val outputData = output as Data.Result<WeatherData>
        assertEquals(outputData.data.cityId, inputData.cityId)
        assertEquals(outputData.data.cityName, inputData.cityName)
        assertEquals(outputData.data.main, inputData.weather.first().main)
        assertEquals(outputData.data.description, inputData.weather.first().description)
        assertEquals(outputData.data.temperature, inputData.main.temperature, DELTA)
        assertEquals(
            outputData.data.temperatureFeelsLike,
            inputData.main.temperatureFeelsLike,
            DELTA
        )
        assertEquals(outputData.data.temperatureMin, inputData.main.temperatureMin, DELTA)
        assertEquals(outputData.data.temperatureMax, inputData.main.temperatureMax, DELTA)
        assertEquals(outputData.data.pressure, inputData.main.pressure)
        assertEquals(outputData.data.humidity, inputData.main.humidity)
        assertEquals(outputData.data.windSpeed, inputData.wind.windSpeed, DELTA)
        assertEquals(outputData.data.windDeg, inputData.wind.windDeg)
        assertEquals(outputData.data.cloudsCoverage, inputData.clouds.cloundCoverage)
        assertEquals(outputData.data.sunriseTime, inputData.sys.sunriseTime)
        assertEquals(outputData.data.sunsetTime, inputData.sys.sunsetTime)
        assertEquals(outputData.data.iconUrl, inputData.weather.first().icon)
    }

    private fun createApiData(): WeatherDataNetworkEntry =
        WeatherDataNetworkEntry(
            coord = Coord(20.47, 44.8),
            weather = listOf(
                Weather(
                    weather_id = 701,
                    main = "Mist",
                    description = "mist",
                    icon = "50d"
                )
            ),
            base = "stations",
            main = Main(
                temperature = 1.0,
                temperatureFeelsLike = -1.7,
                temperatureMin = 1.0,
                temperatureMax = 1.0,
                pressure = 1022,
                humidity = 76
            ),
            visibility = 3000,
            wind = Wind(windSpeed = 0.5, windDeg = 0),
            clouds = Clouds(cloundCoverage = 90),
            dt = 1606566368,
            sys = Sys(
                type = 1,
                sys_id = 7028,
                country = "RS",
                sunriseTime = 1606542746,
                sunsetTime = 1606575620
            ), timezone = 3600,
            cityId = 792680,
            cityName = "Belgrade",
            cod = 200
        )

    @Test
    fun test_mapping_ApiResponseApiError_to_WeatherData() {
        val mapper = NetworkEntityMapper()
        val inputData: OpenWeatherApiError = createApiError()
        val apiErrorResponse = ApiResponse.ApiError(inputData)

        val output: Data<WeatherData> =
            mapper.mapFromApiResponse(apiErrorResponse, FAKE_CURRENT_TIME)

        assertTrue(output is Data.Error)
        val outputData = output as Data.Error
        assertEquals(UNAUTHORISED_ERROR_MESSAGE, outputData.message)
    }

    private fun createApiError(): OpenWeatherApiError =
        OpenWeatherApiError(UNAUTHORISED_ERROR_CODE, UNAUTHORISED_ERROR_MESSAGE)
}