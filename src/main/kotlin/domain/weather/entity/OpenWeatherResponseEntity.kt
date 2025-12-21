package com.despaircorp.domain.weather.entity

import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherResponseEntity(
    val city: String,
    val country: String,
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val description: String,
    val icon: String,
    val windSpeed: Double,
    val pressure: Int,
    val visibility: Int,
    val sunrise: Long,
    val sunset: Long,
    val condition: String
)