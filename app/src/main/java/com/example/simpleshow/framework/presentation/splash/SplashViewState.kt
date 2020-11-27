package com.example.simpleshow.framework.presentation.splash

sealed class SplashViewState {

    object Idle : SplashViewState()

    object Loading : SplashViewState()

    object NavigateToWeatherPage : SplashViewState()

    data class Error(val errorMessage: String? = null) : SplashViewState()
}