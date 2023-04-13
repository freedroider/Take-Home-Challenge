package com.example.presentation.screens

import androidx.lifecycle.viewModelScope
import com.example.common.AppLogger
import com.example.domain.entity.RadioData
import com.example.domain.entity.RadioElement
import com.example.domain.entity.Type
import com.example.domain.use_case.GetRadioDataUseCase
import com.example.presentation.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getRadioDataUseCase: GetRadioDataUseCase
) : StateViewModel<DashboardScreenState, DashboardEvent, DashboardEffect>() {
    private val radioData = MutableStateFlow<RadioData?>(null)
    private val loading = MutableStateFlow(false)

    override val screenState: StateFlow<DashboardScreenState> = combine(radioData, loading) { radioData, loading ->
        when {
            loading -> DashboardScreenState.Loading
            radioData == null -> DashboardScreenState.Default
            else -> DashboardScreenState.Loaded(radioData.body)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = DashboardScreenState.Default
    )

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnRadioElementClick -> handleRadioElementClick(event.item)
            is DashboardEvent.OnSetup -> loadData(event.url)
        }
    }

    private fun loadData(url: String) = viewModelScope.launch(Dispatchers.Main) {
        if (radioData.value != null || loading.value) return@launch
        try {
            loading.emit(true)
            val data = withContext(Dispatchers.IO) {
                getRadioDataUseCase(url)
            }
            radioData.emit(data)
        } catch (throwable: Throwable) {
            sendEffect(DashboardEffect.Error(throwable))
        } finally {
            loading.emit(false)
        }
    }

    private fun handleRadioElementClick(item: RadioElement) = viewModelScope.launch(Dispatchers.Main) {
        item.url ?: return@launch
        when (item.type) {
            is Type.Link -> pushEffect(DashboardEffect.OnNavigateToRadioElement(item))
            is Type.Audio -> pushEffect(DashboardEffect.OnPlayAudio(item))
            else -> AppLogger.i("handleRadioElementClick ignore type: ${item.type}")
        }
    }
}