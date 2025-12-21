package com.despaircorp.domain.weather

import com.despaircorp.domain.error_manager.ErrorManager
import com.despaircorp.domain.error_manager.ErrorManagerException
import com.despaircorp.domain.weather.entity.OpenWeatherResponseEntity

class RequestWeatherApiUseCase(
    private val openWeatherRepository: OpenWeatherRepository,
) {
    suspend operator fun invoke(
        lat: Double,
        long: Double,
        language: String
    ): Result<OpenWeatherResponseEntity> = openWeatherRepository.getWeather(lat, long, language)
        ?.let { Result.success(it) }
        ?: Result.failure(ErrorManagerException(ErrorManager.REQUEST_ERROR))

}