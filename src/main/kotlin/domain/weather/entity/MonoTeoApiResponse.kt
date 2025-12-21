package com.despaircorp.domain.weather.entity

import kotlinx.serialization.Serializable

@Serializable
data class MonoTeoApiResponse(
    val openWeatherResponseResult: OpenWeatherResponseEntity,
    val sentence: String
)
