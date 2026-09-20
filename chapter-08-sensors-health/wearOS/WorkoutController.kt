package com.example.handsonwearables.chapter08

import android.content.Context
import androidx.health.services.client.HealthServices
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.ExerciseConfig
import androidx.health.services.client.data.ExerciseType
import kotlin.time.Duration

// Book: Chapter 8, pp.88 and 90.
class WorkoutController(context: Context) {
    private val health = HealthServices.getClient(context)
    private val exerciseClient = health.exerciseClient

    fun startWalk() {
        val config = ExerciseConfig.Builder(ExerciseType.WALKING)
            .setDataTypes(setOf(DataType.HEART_RATE_BPM))
            .setIsAutoPauseAndResumeEnabled(false)
            .setIsGpsEnabled(false)
            .build()

        exerciseClient.startExerciseAsync(config)
    }

    fun endWalk() {
        exerciseClient.endExerciseAsync()
    }
}

data class WorkoutUiState(
    val bpm: Int?,
    val elapsed: Duration,
    val isPaused: Boolean
)
