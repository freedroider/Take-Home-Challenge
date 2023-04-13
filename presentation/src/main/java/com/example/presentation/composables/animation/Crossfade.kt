package com.example.presentation.composables.animation

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.core.ScreenState
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FiniteAnimationSpec

@Composable
fun <S : ScreenState> Crossfade(
    screenState: S,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    contentKey: (targetState: S) -> Any = { it::class },
    content: @Composable (targetState: S) -> Unit,
) {
    val transition = updateTransition(targetState = screenState, label = "Content Animation")
    transition.Crossfade(
        modifier = modifier,
        animationSpec = animationSpec,
        contentKey = contentKey,
        content = content,
    )
}