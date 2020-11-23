package com.example.simpleshow.framework.presentation.common

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.simpleshow.business.domain.StateAction
import com.example.simpleshow.business.domain.store.ViewStateStore
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State : Any, Store : ViewStateStore<State>, Action : StateAction> :
    ViewModel() {

    protected abstract val stateHandle: SavedStateHandle
    protected abstract val store: Store

    val viewState: StateFlow<State> by lazy { store.stateFlow }

    abstract fun onAction(action: Action)

    override fun onCleared() {
        super.onCleared()
        store.cancel()
    }
}