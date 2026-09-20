package com.example.pocketbrief.shared

import kotlinx.serialization.Serializable

// Book: Chapter 9, p.96.
@Serializable
data class WristBrief(
    val displayName: String,
    val statusText: String,
    val nextAppointmentEpochMs: Long?,
    val alertCount: Int,
    val updatedAtEpochMs: Long,
    val isSensitiveHidden: Boolean
)
