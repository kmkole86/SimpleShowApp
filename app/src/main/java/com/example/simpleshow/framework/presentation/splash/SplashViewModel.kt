package com.example.simpleshow.framework.presentation.splash

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.example.simpleshow.business.domain.StateAction
import com.example.simpleshow.business.domain.usecase.splash.FetchWeatherData
import com.example.simpleshow.framework.presentation.common.BaseViewModel
import com.example.simpleshow.framework.presentation.weather.ActionGetWeatherData


class SplashViewModel
@ViewModelInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    store: SplashStore,
    private val fetchWeatherData: FetchWeatherData
) : BaseViewModel<SplashViewState, StateAction.SplashAction>(stateHandle, store) {

    init {
        onAction(ActionGetWeatherData)
    }

    override fun onAction(action: StateAction.SplashAction) {
        when (action) {
            is ActionGetWeatherData -> store.dispatchState(fetchWeatherData())
        }
    }
}