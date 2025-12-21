package com.despaircorp.domain.weather

import com.despaircorp.domain.weather.entity.OpenWeatherRequestEntity
import io.ktor.http.content.MultiPartData
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart

class UnwrapWeatherMultipartUseCase() {

    suspend operator fun invoke(multipart: MultiPartData): OpenWeatherRequestEntity? {

        var latitude: String? = null
        var longitude: String? = null
        var language: String? = null

        multipart.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    when (part.name) {
                        "latitude" -> latitude = part.value
                        "longitude" -> longitude = part.value
                        "language" -> language = part.value
                    }
                }
                else -> {}
            }
            part.dispose()
        }

        val openWeatherRequestEntity = OpenWeatherRequestEntity(
            latitude = latitude?.toDoubleOrNull() ?: return null,
            longitude = longitude?.toDoubleOrNull() ?: return null,
            language = language ?: return null
        )

        return openWeatherRequestEntity

    }


}