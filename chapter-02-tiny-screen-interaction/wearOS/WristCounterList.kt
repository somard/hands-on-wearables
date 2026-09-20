package com.example.handsonwearables.chapter02

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text

// Complete file around the book's p.28 ScalingLazyColumn snippet.
@Composable
fun WristCounterList() {
    var count by remember { mutableIntStateOf(0) }
    val history = remember(count) { (1..count).map { "Count reached $it" } }

    ScalingLazyColumn {
        item { Text("Count: $count") }
        item { Button(onClick = { count++ }) { Text("+1") } }
        items(history) { entry ->
            Text(entry)
        }
    }
}
