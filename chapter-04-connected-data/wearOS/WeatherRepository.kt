package com.example.handsonwearables.chapter04

// Minimal cache-backed repository around the exact model/API used in the book.
class WeatherRepository(private val api: WeatherApi) {
    private var cache: CachedWeather? = null

    suspend fun refresh(): WeatherUiState = try {
        val weather = api.now()
        cache = CachedWeather(weather, System.currentTimeMillis())
        WeatherUiState.Ready(weather)
    } catch (_: Exception) {
        WeatherUiState.Error(cache?.value)
    }

    fun cached(): CachedWeather? = cache
}
