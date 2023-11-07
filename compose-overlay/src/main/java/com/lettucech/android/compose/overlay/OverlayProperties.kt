package com.lettucech.android.compose.overlay

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.ui.graphics.Color

data class OverlayProperties(
    val dismissOnBackPress: Boolean,
    val dismissOnClickOutside: Boolean,
    val enterTransition: EnterTransition,
    val exitTransition: ExitTransition,
    val scrimColor: Color,
    val scrimAnimationSpec: AnimationSpec<Float>
)