package com.lettucech.android.compose.overlay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lettucech.android.compose.overlay.ui.theme.AndroidComposeOverlayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeOverlayTheme {
                AndroidComposeOverlayApp {
                    SampleScreen()
                }
            }
        }
    }
}

@Composable
fun AndroidComposeOverlayApp(
    content: @Composable () -> Unit
) {
    OverlayScaffold {
        Surface(
            color = MaterialTheme.colorScheme.background,
            content = content,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun SampleScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var showOverlay by remember { mutableStateOf(false) }

        if (showOverlay) {
            Overlay(onDismissRequest = { showOverlay = false }) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Hello, this is overlay")
                }
            }
        }

        Button(onClick = {
            println("test")
            showOverlay = true
        }) {
            Text(text = "Show Overlay")
        }
    }
}