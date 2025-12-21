package com.despaircorp.data.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val visibility: Int,
    val sys: Sys
)

@Serializable
data class Main(
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    val humidity: Int,
    val pressure: Int
)

@Serializable
data class Weather(
    val description: String,
    val main: String,
    val icon: String
)

@Serializable
data class Wind(
    val speed: Double
)

@Serializable
data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)