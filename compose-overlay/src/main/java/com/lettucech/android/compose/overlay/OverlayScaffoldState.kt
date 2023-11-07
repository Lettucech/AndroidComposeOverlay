package com.lettucech.android.compose.overlay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class OverlayScaffoldState {
    internal var overlayWrappers = mutableStateOf<List<OverlayWrapper>>(emptyList())
        private set

    internal fun add(wrapper: OverlayWrapper) {
        overlayWrappers.value = overlayWrappers.value + wrapper
    }

    internal fun remove(wrapper: OverlayWrapper) {
        overlayWrappers.value = overlayWrappers.value - wrapper
    }
}

@Composable
fun rememberOverlayScaffoldState() = remember { OverlayScaffoldState() }