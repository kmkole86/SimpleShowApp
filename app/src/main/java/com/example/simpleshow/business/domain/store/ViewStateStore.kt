package com.example.simpleshow.business.domain.store

import androidx.annotation.MainThread
import com.example.simpleshow.business.domain.Reducer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

abstract class ViewStateStore<State : Any>(initialState: State) :
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private val _stateFlow = MutableStateFlow<State>(initialState)
    val stateFlow: StateFlow<State> get() = _stateFlow

    private fun state(): State {
        return stateFlow.value
    }

    @MainThread
    fun dispatchState(newState: State) {
        _stateFlow.value = newState
    }

    fun dispatchState(flow: Flow<State>) {
        launch {
            flow.flowOn(Dispatchers.Main)
                .collect { dispatchState(it) }
        }
    }

    fun dispatchState(f: suspend (State) -> Reducer<State>) {
        launch {
            val reducer = f(state())
            withContext(Dispatchers.Main) {
                dispatchState(reducer(state()))
            }
        }
    }

    @JvmName("dispatchStateFlowReducer")
    fun dispatchState(flow: Flow<Reducer<State>>) {
        launch {
            flow.flowOn(Dispatchers.Main)
                .collect { reducer: Reducer<State> -> dispatchState(reducer(state())) }
        }
    }

    fun dispatchState(channel: ReceiveChannel<Reducer<State>>) {
        launch {
            for (reducer in channel) {
                withContext(Dispatchers.Main) {
                    dispatchState(reducer(state()))
                }
            }
        }
    }

    fun cancel() = job.cancel()
}