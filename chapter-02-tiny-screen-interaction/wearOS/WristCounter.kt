package com.example.handsonwearables.chapter02

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text

// Book: Chapter 2, p.26
@Composable
fun WristCounter() {
    var count by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Count")
        Text("$count")
        Button(onClick = { count++ }) {
            Text("+1")
        }
        Button(onClick = { count = 0 }) {
            Text("Reset")
        }
    }
}
