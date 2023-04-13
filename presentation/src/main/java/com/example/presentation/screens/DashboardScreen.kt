package com.example.presentation.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.domain.entity.RadioElement
import com.example.presentation.composables.animation.Crossfade
import com.example.presentation.composables.elements.NavigationText
import com.example.presentation.composables.items.RadioElementItem
import com.example.presentation.themes.AppTheme
import com.example.presentation.utils.toast

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    title: String,
    url: String,
    backEnabled: Boolean,
    onBack: () -> Unit,
    navigateToRadioElement: (RadioElement) -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()
    val activity = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        viewModel.effects
            .collect { effect ->
                when (effect) {
                    is DashboardEffect.OnNavigateToRadioElement -> navigateToRadioElement(effect.radioElement)
                    is DashboardEffect.OnPlayAudio -> activity.toast("play ${effect.radioElement.url}")
                    is DashboardEffect.Error -> activity.toast(effect.throwable)
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(DashboardEvent.OnSetup(url))
    }

    DashboardScreenContent(
        screenState = screenState,
        title = title,
        backEnabled = backEnabled,
        onBack = onBack,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun DashboardScreenContent(
    screenState: DashboardScreenState,
    title: String,
    backEnabled: Boolean,
    onBack: () -> Unit,
    onEvent: (DashboardEvent) -> Unit
) {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            AppBar(
                backEnabled = backEnabled,
                onBack = onBack,
            )
            NavigationText(
                title = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(16.dp)
            )
            Crossfade(screenState = screenState) { state ->
                when (state) {
                    is DashboardScreenState.Default -> Default()
                    is DashboardScreenState.Loading -> Loading()
                    is DashboardScreenState.Loaded -> Loaded(
                        items = state.radioElements,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}

@Composable
private fun AppBar(
    backEnabled: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (backEnabled) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun Default() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Data is not loaded")
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Loaded(
    items: List<RadioElement>,
    onEvent: (DashboardEvent) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items.forEach { item ->
            item {
                RadioElementItem(
                    item = item,
                    onEvent = onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 54.dp)
                )
            }
            item.children?.let { children ->
                children.forEach { child ->
                    item {
                        RadioElementItem(
                            item = child,
                            onEvent = onEvent,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .heightIn(min = 32.dp)
                        )
                    }
                }
            }
            item { Divider(modifier = Modifier.padding(horizontal = 16.dp)) }
        }
    }
}