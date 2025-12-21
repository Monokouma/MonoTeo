package com.despaircorp.domain.weather.entity

data class OpenWeatherRequestEntity(
    val latitude: Double,
    val longitude: Double,
    val language: String
)
