package com.example.simpleshow.business.domain

sealed class StateAction {

    open class SplashAction : StateAction()

    open class WeatherAction : StateAction()
}