package com.despaircorp.data.weather

import com.despaircorp.data.weather.dto.OpenWeatherResponse
import com.despaircorp.data.weather.mapper.toEntity
import com.despaircorp.domain.weather.OpenWeatherRepository
import com.despaircorp.domain.weather.entity.OpenWeatherResponseEntity
import com.despaircorp.utils.MonoTeoLogger
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OpenWeatherRepositoryImpl(
    private val httpClient: HttpClient,
    private val dotenv: Dotenv,
): OpenWeatherRepository {

    override suspend fun getWeather(
        lat: Double,
        long: Double,
        lang: String
    ): OpenWeatherResponseEntity? = withContext(Dispatchers.IO) {
        try {
            val response = httpClient.get("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$long&appid=${dotenv["OW_KEY"]}&units=metric&lang=$lang") {
                timeout {
                    requestTimeoutMillis = 5000
                    connectTimeoutMillis = 3000
                }
            }

            response.body<OpenWeatherResponse>().toEntity()

        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}