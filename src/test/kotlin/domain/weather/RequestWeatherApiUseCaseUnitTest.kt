package domain.weather

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.despaircorp.data.weather.dto.Main
import com.despaircorp.data.weather.dto.OpenWeatherResponse
import com.despaircorp.data.weather.dto.Sys
import com.despaircorp.data.weather.dto.Weather
import com.despaircorp.data.weather.dto.Wind
import com.despaircorp.domain.error_manager.ErrorManagerException
import com.despaircorp.domain.weather.OpenWeatherRepository
import com.despaircorp.domain.weather.RequestWeatherApiUseCase
import com.despaircorp.domain.weather.entity.OpenWeatherResponseEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RequestWeatherApiUseCaseUnitTest {
    private val openWeatherRepository: OpenWeatherRepository = mockk()

    private val useCase: RequestWeatherApiUseCase = RequestWeatherApiUseCase(openWeatherRepository)

    @BeforeEach
    fun setUp() {
        coEvery { openWeatherRepository.getWeather(
            lat = DEFAULT_LAT,
            long = DEFAULT_LON,
            lang = DEFAULT_LANG
        ) } returns provideOpenWeatherResponseEntity()
    }

    @Test
    fun `nominal case - should return OpenWeatherResponse`() = runTest {
        val result = useCase.invoke(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)

        assertThat(result).isEqualTo(Result.success(provideOpenWeatherResponseEntity()))

        coVerify {
            openWeatherRepository.getWeather(
                DEFAULT_LAT,
                DEFAULT_LON,
                DEFAULT_LANG
            )
        }

        confirmVerified(openWeatherRepository)
    }

    @Test
    fun `error case - should return null`() = runTest {
        coEvery { openWeatherRepository.getWeather(
            lat = DEFAULT_LAT,
            long = DEFAULT_LON,
            lang = DEFAULT_LANG
        ) } returns null

        val result = useCase.invoke(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)

        assertThat(result)
            .isFailure()
            .isInstanceOf(ErrorManagerException::class)
            .hasMessage("Request error")

        coVerify {
            openWeatherRepository.getWeather(
                DEFAULT_LAT,
                DEFAULT_LON,
                DEFAULT_LANG
            )
        }

        confirmVerified(openWeatherRepository)
    }


    fun provideDefaultOpenWeatherResponse() = OpenWeatherResponse(
        name = DEFAULT_CITY,
        main = Main(
            temp = DEFAULT_TEMPERATURE,
            feelsLike = DEFAULT_FEELS_LIKE,
            humidity = DEFAULT_HUMIDITY,
            pressure = DEFAULT_PRESSURE
        ),
        weather = listOf(
            Weather(
                description = DEFAULT_DESCRIPTION,
                main = DEFAULT_CONDITION,
                icon = DEFAULT_ICON_CODE
            )
        ),
        wind = Wind(speed = DEFAULT_WIND_SPEED),
        visibility = DEFAULT_VISIBILITY,
        sys = Sys(
            country = DEFAULT_COUNTRY,
            sunrise = DEFAULT_SUNRISE,
            sunset = DEFAULT_SUNSET
        )
    )

    private fun provideOpenWeatherResponseEntity(): OpenWeatherResponseEntity = OpenWeatherResponseEntity(
        city = DEFAULT_CITY,
        country = DEFAULT_COUNTRY,
        temperature = DEFAULT_TEMPERATURE,
        feelsLike = DEFAULT_FEELS_LIKE,
        humidity = DEFAULT_HUMIDITY,
        description = DEFAULT_DESCRIPTION,
        icon = DEFAULT_ICON,
        windSpeed = DEFAULT_WIND_SPEED,
        pressure = DEFAULT_PRESSURE,
        visibility = DEFAULT_VISIBILITY,
        sunrise = DEFAULT_SUNRISE,
        sunset = DEFAULT_SUNSET,
        condition = DEFAULT_CONDITION
    )

    companion object {
        private const val API_KEY = "API_KEY"
        private const val DEFAULT_LAT = 48.8566
        private const val DEFAULT_LON = 2.3522
        private const val DEFAULT_LANG = "fr"
        private const val DEFAULT_CITY = "Paris"
        private const val DEFAULT_COUNTRY = "FR"
        private const val DEFAULT_TEMPERATURE = 18.5
        private const val DEFAULT_FEELS_LIKE = 17.2
        private const val DEFAULT_HUMIDITY = 65
        private const val DEFAULT_DESCRIPTION = "ciel dégagé"
        private const val DEFAULT_ICON = "https://openweathermap.org/img/wn/01d@2x.png"
        private const val DEFAULT_WIND_SPEED = 3.5
        private const val DEFAULT_PRESSURE = 1013
        private const val DEFAULT_VISIBILITY = 10000
        private const val DEFAULT_SUNRISE = 10L
        private const val DEFAULT_SUNSET = 22L
        private const val DEFAULT_ICON_CODE = "01d"
        private const val DEFAULT_CONDITION = "Rain"
        private const val DEFAULT_WEATHER_PHRASE = "DEFAULT_WEATHER_PHRASE"
    }
}