package com.lettucech.android.compose.overlay

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.collectLatest
import java.util.UUID

@Composable
fun OverlayScaffold(
    state: OverlayScaffoldState = rememberOverlayScaffoldState(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalOverlayScaffoldState provides state
    ) {
        content()
        state.overlayWrappers.value.forEach { wrapper ->
            key(wrapper.id) {
                val isVisible by produceState(initialValue = false) {
                    snapshotFlow { wrapper.isActive }.collectLatest { value = it }
                }

                BackHandler(enabled = wrapper.properties.dismissOnBackPress && wrapper.isActive) {
                    wrapper.onDismissRequest()
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Scrim(
                        color = wrapper.properties.scrimColor,
                        animationSpec = wrapper.properties.scrimAnimationSpec,
                        onDismiss = {
                            if (wrapper.properties.dismissOnClickOutside && wrapper.isActive) {
                                wrapper.onDismissRequest()
                            }
                        },
                        visible = isVisible
                    )
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = wrapper.properties.enterTransition,
                        exit = wrapper.properties.exitTransition
                    ) {
                        DisposableEffect(Unit) {
                            onDispose {
                                state.remove(wrapper)
                            }
                        }
                        wrapper.content()
                    }
                }
            }
        }
    }
}

internal class OverlayWrapper(
    val id: UUID,
    val onDismissRequest: () -> Unit,
    val properties: OverlayProperties,
    val content: @Composable () -> Unit
) {
    var isActive by mutableStateOf(true)
        private set

    fun dismiss() {
        isActive = false
    }
}

internal val LocalOverlayScaffoldState = compositionLocalOf { OverlayScaffoldState() }