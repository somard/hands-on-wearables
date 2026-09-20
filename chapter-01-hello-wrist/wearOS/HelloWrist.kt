package com.example.handsonwearables.chapter01

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text

// Book: Chapter 1, p.20
@Composable
fun HelloWrist() {
    var taps by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello, Wrist!")
        Text("Taps: $taps")
        Button(onClick = { taps++ }) {
            Text("Tap")
        }
    }
}
