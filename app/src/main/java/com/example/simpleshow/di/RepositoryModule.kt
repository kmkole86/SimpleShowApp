package com.example.simpleshow.di

import android.content.Context
import androidx.room.Room
import com.example.simpleshow.BuildConfig
import com.example.simpleshow.business.data.cache.abstraction.WeatherCacheDataSource
import com.example.simpleshow.business.data.cache.implementation.WeatherCacheDataSourceImpl
import com.example.simpleshow.business.data.network.abstraction.WeatherNetworkDataSource
import com.example.simpleshow.business.data.network.implementation.WeatherNetworkDataSourceImpl
import com.example.simpleshow.framework.datasource.cache.abstraction.WeatherDaoService
import com.example.simpleshow.framework.datasource.cache.database.WeatherDataDao
import com.example.simpleshow.framework.datasource.cache.database.WeatherDatabase
import com.example.simpleshow.framework.datasource.cache.implementation.WeatherDaoServiceImpl
import com.example.simpleshow.framework.datasource.network.abstraction.WeatherNetworkService
import com.example.simpleshow.framework.datasource.network.implementation.WeatherNetworkServiceImpl
import com.example.simpleshow.framework.datasource.network.retrofit.WeatherApiService
import com.example.simpleshow.framework.datasource.network.retrofit.adapters.ApiResponseAdapterFactory
import com.example.simpleshow.framework.datasource.network.retrofit.interceptors.AuthInterceptor
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
abstract class AbstractionsModule {

    @Singleton
    @Binds
    abstract fun bindWeatherNetworkDataSource(dataSourceImpl: WeatherNetworkDataSourceImpl): WeatherNetworkDataSource

    @Singleton
    @Binds
    abstract fun bindWeatherCacheDataSource(dataSourceImpl: WeatherCacheDataSourceImpl): WeatherCacheDataSource

    @Singleton
    @Binds
    abstract fun bindWeatherDaoService(weatherDaoService: WeatherDaoServiceImpl): WeatherDaoService

    @Singleton
    @Binds
    abstract fun bindWeatherNetworkService(weatherNetworkService: WeatherNetworkServiceImpl): WeatherNetworkService
}

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Singleton
    @Provides
    fun provideAuthInterceptor(): AuthInterceptor =
        AuthInterceptor()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): Retrofit {
        return Retrofit.Builder()
            .client(
                okHttpClient.newBuilder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(loggingInterceptor).build()
            )
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)

    @Singleton
    @Provides
    fun provideWeatherDataDao(weatherDatabase: WeatherDatabase): WeatherDataDao =
        weatherDatabase.weatherDataDao()

    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room
            .databaseBuilder(appContext, WeatherDatabase::class.java, WeatherDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}