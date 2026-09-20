package com.example.pocketbrief

// Pre-existing phone-only model. Chapter 9 later projects this richer phone state
// into the smaller WristBrief companion model.
data class PocketBriefModel(
    val displayName: String = "Taylor",
    val statusText: String = "Available",
    val nextAppointmentEpochMs: Long? = null,
    val alertCount: Int = 1,
    val signedIn: Boolean = true
)
