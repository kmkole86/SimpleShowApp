package com.example.simpleshow.framework.presentation.weather

import com.example.simpleshow.business.domain.store.ViewStateStore
import javax.inject.Inject


class WeatherStore @Inject constructor() :
    ViewStateStore<WeatherViewState>(WeatherViewState())