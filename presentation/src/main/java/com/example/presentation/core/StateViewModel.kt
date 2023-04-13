package com.example.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<S : ScreenState, Ev : Event, Ef : Effect> : ViewModel() {
    abstract val screenState: StateFlow<S>

    private val _effects = MutableSharedFlow<Ef>()
    val effects = _effects.asSharedFlow()

    open fun onEvent(event: Ev) {
        throw UnsupportedOperationException()
    }

    protected fun pushEffect(effect: Ef) = viewModelScope.launch { sendEffect(effect) }

    protected suspend fun sendEffect(effect: Ef) {
        _effects.emit(effect)
    }
}

interface ScreenState

interface Event

interface Effect