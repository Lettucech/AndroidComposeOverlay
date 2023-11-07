package com.lettucech.android.compose.overlay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import java.util.UUID

@Composable
fun Overlay(
    onDismissRequest: () -> Unit,
    properties: OverlayProperties = OverlayDefaults.properties(),
    content: @Composable () -> Unit
) {
    val scaffoldState = LocalOverlayScaffoldState.current
    val overlayId = rememberSaveable { UUID.randomUUID() }
    val wrapper = remember(onDismissRequest, properties, content) {
        OverlayWrapper(overlayId, onDismissRequest, properties, content)
    }

    DisposableEffect(scaffoldState, wrapper) {
        scaffoldState.add(wrapper)
        onDispose {
            wrapper.dismiss()
        }
    }
}
