package com.example.handsonwearables.chapter02

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusable
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.wear.compose.material3.Text

// Complete component around the book's p.30 Modifier chain.
@Composable
fun RotaryValue() {
    var count by remember { mutableIntStateOf(0) }
    val focusRequester = remember { FocusRequester() }

    Text(
        text = count.toString(),
        modifier = Modifier
            .onRotaryScrollEvent { event ->
                count += if (event.verticalScrollPixels > 0) 1 else -1
                count = count.coerceIn(0, 20)
                true
            }
            .focusRequester(focusRequester)
            .focusable()
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
