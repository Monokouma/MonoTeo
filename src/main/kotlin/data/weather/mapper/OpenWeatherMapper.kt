package com.despaircorp.data.weather.mapper

import com.despaircorp.data.weather.dto.OpenWeatherResponse
import com.despaircorp.domain.weather.entity.OpenWeatherResponseEntity

fun OpenWeatherResponse.toEntity(): OpenWeatherResponseEntity {
    return OpenWeatherResponseEntity(
        city = name,
        country = sys.country,
        temperature = main.temp,
        feelsLike = main.feelsLike,
        humidity = main.humidity,
        description = weather.firstOrNull()?.description.orEmpty(),
        icon = "https://openweathermap.org/img/wn/${weather.firstOrNull()?.icon}@2x.png",
        windSpeed = wind.speed,
        pressure = main.pressure,
        visibility = visibility,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        condition = weather.firstOrNull()?.main.orEmpty()
    )
}