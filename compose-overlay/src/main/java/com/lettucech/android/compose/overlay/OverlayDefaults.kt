package com.lettucech.android.compose.overlay

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

@Immutable
object OverlayDefaults {
    const val DismissOnBackPress = true

    const val DismissOnClickOutside = true

    val EnterTransition = fadeIn(animationSpec = TweenSpec(300))

    val ExitTransition = fadeOut(animationSpec = TweenSpec(300))

    val ScrimColor: Color
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.32f)

    val ScrimAnimationSpec: AnimationSpec<Float> = TweenSpec(300)

    @Composable
    fun properties(
        dismissOnBackPress: Boolean = DismissOnBackPress,
        dismissOnClickOutside: Boolean = DismissOnClickOutside,
        enterTransition: EnterTransition = EnterTransition,
        exitTransition: ExitTransition = ExitTransition,
        scrimColor: Color = ScrimColor,
        scrimAnimationSpec: AnimationSpec<Float> = ScrimAnimationSpec
    ) = OverlayProperties(
        dismissOnBackPress,
        dismissOnClickOutside,
        enterTransition,
        exitTransition,
        scrimColor,
        scrimAnimationSpec
    )
}
