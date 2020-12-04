package com.example.simpleshow.framework.presentation.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.simpleshow.business.domain.StateAction
import com.example.simpleshow.business.domain.store.ViewStateStore
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State : Any, Action : StateAction>(
    protected val stateHandle: SavedStateHandle,
    protected val store: ViewStateStore<State>
) : ViewModel() {

    val viewState: StateFlow<State> = store.stateFlow

    abstract fun onAction(action: Action)

    override fun onCleared() {
        super.onCleared()
        store.cancel()
    }
}