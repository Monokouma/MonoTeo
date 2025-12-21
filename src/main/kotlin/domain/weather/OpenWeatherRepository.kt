package com.despaircorp.domain.weather

import com.despaircorp.data.weather.dto.OpenWeatherResponse
import com.despaircorp.domain.weather.entity.OpenWeatherResponseEntity

interface OpenWeatherRepository {
    suspend fun getWeather(lat: Double, long: Double, lang: String): OpenWeatherResponseEntity?
}