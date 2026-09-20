package com.example.pocketbrief.wear

import androidx.compose.runtime.Composable
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text
import com.example.pocketbrief.shared.WristBrief

// Book: Chapter 9, p.98.
@Composable
fun PocketBriefScreen(
    brief: WristBrief,
    openDetailsOnPhone: () -> Unit
) {
    ScalingLazyColumn {
        item { Text("Good morning, ${brief.displayName}") }
        item { StatusCard(brief.statusText) }
        brief.nextAppointmentEpochMs?.let { time ->
            item { AppointmentCard(time) }
        }
        item { Button(onClick = openDetailsOnPhone) {
            Text("Open details on phone")
        }}
    }
}

@Composable
private fun StatusCard(status: String) {
    Text(status)
}

@Composable
private fun AppointmentCard(time: Long) {
    Text("Appointment: $time")
}
