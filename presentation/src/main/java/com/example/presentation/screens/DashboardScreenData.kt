package com.example.presentation.screens

import com.example.domain.entity.RadioElement
import com.example.presentation.core.Effect
import com.example.presentation.core.Event
import com.example.presentation.core.ScreenState
import javax.annotation.concurrent.Immutable

@Immutable
sealed interface DashboardScreenState : ScreenState {
    object Default : DashboardScreenState
    object Loading : DashboardScreenState
    data class Loaded(val radioElements: List<RadioElement>) : DashboardScreenState
}

@Immutable
sealed interface DashboardEvent : Event {
    data class OnSetup(val url: String) : DashboardEvent
    data class OnRadioElementClick(val item: RadioElement) : DashboardEvent
}

@Immutable
sealed interface DashboardEffect : Effect {
    data class OnNavigateToRadioElement(val radioElement: RadioElement) : DashboardEffect
    data class OnPlayAudio(val radioElement: RadioElement) : DashboardEffect
    data class Error(val throwable: Throwable) : DashboardEffect
}