package com.example.handsonwearables.chapter04

import retrofit2.http.GET

// Book: Chapter 4, p.46
interface WeatherApi {
    @GET("wear/now")
    suspend fun now(): WeatherNow
}
