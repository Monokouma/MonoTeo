package com.despaircorp.domain.weather

import com.despaircorp.domain.authorization.ValidateApiKeyUseCase
import com.despaircorp.domain.error_manager.ErrorManager
import com.despaircorp.domain.error_manager.ErrorManagerException
import com.despaircorp.domain.weather.entity.MonoTeoApiResponse
import com.despaircorp.utils.MonoTeoLogger
import io.ktor.http.content.MultiPartData

class ManageGetWeatherRequestUseCase(
    private val validateApiKeyUseCase: ValidateApiKeyUseCase,
    private val unwrapWeatherMultipartUseCase: UnwrapWeatherMultipartUseCase,
    private val requestWeatherApiUseCase: RequestWeatherApiUseCase
) {
    suspend operator fun invoke(token: String?, multipart: MultiPartData): Result<MonoTeoApiResponse> {
        val isAuthorized = validateApiKeyUseCase.invoke(
            token
        ).onFailure {
            return Result.failure(it)
        }.getOrElse {
            return Result.failure(it)
        }

        if (isAuthorized.not()) return Result.failure(ErrorManagerException(ErrorManager.WRONG_TOKEN))

        val openWeatherRequestEntity = unwrapWeatherMultipartUseCase.invoke(multipart) ?:
            return Result.failure(ErrorManagerException(ErrorManager.REQUEST_ERROR))

        val openWeatherResponseResult = requestWeatherApiUseCase.invoke(
            lat = openWeatherRequestEntity.latitude,
            long = openWeatherRequestEntity.longitude,
            language = openWeatherRequestEntity.language
        ).onFailure {
            return Result.failure(it)
        }.getOrElse {
            return Result.failure(it)
        }

        return Result.success(
            MonoTeoApiResponse(
                openWeatherResponseResult = openWeatherResponseResult,
                sentence = WeatherSentenceGenerator.getWeatherPhrase(
                    condition = openWeatherResponseResult.condition,
                    temp = openWeatherResponseResult.temperature,
                    lang = openWeatherRequestEntity.language
                )
            )
        )
    }
}