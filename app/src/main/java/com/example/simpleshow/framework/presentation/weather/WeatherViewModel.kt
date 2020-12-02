package com.example.simpleshow.framework.presentation.weather

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.example.simpleshow.business.domain.StateAction
import com.example.simpleshow.business.domain.usecase.weather.ShowWeatherData
import com.example.simpleshow.business.domain.usecase.weather.UpdateWeatherData
import com.example.simpleshow.framework.presentation.common.BaseViewModel

class WeatherViewModel @ViewModelInject constructor(
    @Assisted override val stateHandle: SavedStateHandle,
    override val store: WeatherStore,
    private val showWeatherData: ShowWeatherData,
    private val updateWeatherData: UpdateWeatherData
) :
    BaseViewModel<WeatherViewState, StateAction.WeatherAction>() {

    init {
        onAction(ActionShowWeatherData)
    }

    override fun onAction(action: StateAction.WeatherAction) {
        when (action) {
            ActionShowWeatherData -> store.dispatchState(showWeatherData())
            ActionUpdateWeatherData -> store.dispatchState(updateWeatherData())
        }
    }
}