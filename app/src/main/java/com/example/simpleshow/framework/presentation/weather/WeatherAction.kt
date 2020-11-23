package com.example.simpleshow.framework.presentation.weather

import com.example.simpleshow.business.domain.StateAction

object ActionGetWeatherData : StateAction.SplashAction()

object ActionShowWeatherData : StateAction.WeatherAction()

object ActionUpdateWeatherData : StateAction.WeatherAction()
