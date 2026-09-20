package com.example.handsonwearables.chapter03

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.core.app.NotificationCompat
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Text
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

private const val CHANNEL = "focus"

// Book: Chapter 3, pp.36, 38, 40. The visible countdown refresh is intentionally
// separate from the deadline, which remains the source of truth.
@Composable
fun FocusTimer(durationSeconds: Int = 60) {
    val ctx = LocalContext.current
    val haptics = LocalHapticFeedback.current

    val endAt = remember { mutableStateOf<Instant?>(null) }
    val now = remember { mutableStateOf(Clock.System.now()) }
    val secondsLeft = endAt.value?.let { end ->
        (end - now.value).inWholeSeconds.coerceAtLeast(0)
    } ?: 0

    LaunchedEffect(endAt.value) {
        while (endAt.value != null) {
            now.value = Clock.System.now()
            if (secondsLeft <= 0) {
                postCompletionNotification(ctx)
                endAt.value = null
                break
            }
            delay(250)
        }
    }

    Text(if (endAt.value == null) "Ready" else "$secondsLeft s")
    Button(onClick = {
        endAt.value = Clock.System.now() + durationSeconds.seconds
        haptics.performHapticFeedback(HapticFeedbackType.Confirm)
    }) { Text("Start") }
}

private fun postCompletionNotification(ctx: Context) {
    val manager = ctx.getSystemService(NotificationManager::class.java)
    manager.createNotificationChannel(
        NotificationChannel(CHANNEL, "Focus timer", NotificationManager.IMPORTANCE_DEFAULT)
    )

    val openWatchIntent = PendingIntent.getActivity(
        ctx,
        0,
        Intent(ctx, Class.forName("com.example.MainActivity")),
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    val notification = NotificationCompat.Builder(ctx, CHANNEL)
        .setSmallIcon(R.drawable.ic_timer)
        .setContentTitle("Focus complete")
        .setContentText("Your wrist timer finished.")
        .setContentIntent(openWatchIntent)
        .setAutoCancel(true)
        .build()

    manager.notify(1, notification)
}
