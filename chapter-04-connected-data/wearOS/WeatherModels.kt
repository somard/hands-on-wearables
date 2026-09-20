package com.example.handsonwearables.chapter04

import kotlinx.serialization.Serializable

// Book: Chapter 4, pp.46, 48, 50.
@Serializable
data class WeatherNow(
    val temperatureC: Double,
    val condition: String,
    val rainNextHour: Boolean
)

sealed interface WeatherUiState {
    data object Loading : WeatherUiState
    data class Ready(val value: WeatherNow) : WeatherUiState
    data class Error(val cached: WeatherNow?) : WeatherUiState
}

data class CachedWeather(
    val value: WeatherNow,
    val fetchedAtEpochMs: Long
)
