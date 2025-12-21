package domain.weather

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.despaircorp.domain.authorization.ValidateApiKeyUseCase
import com.despaircorp.domain.error_manager.ErrorManager
import com.despaircorp.domain.error_manager.ErrorManagerException
import com.despaircorp.domain.weather.ManageGetWeatherRequestUseCase
import com.despaircorp.domain.weather.RequestWeatherApiUseCase
import com.despaircorp.domain.weather.UnwrapWeatherMultipartUseCase
import com.despaircorp.domain.weather.WeatherSentenceGenerator
import com.despaircorp.domain.weather.entity.MonoTeoApiResponse
import com.despaircorp.domain.weather.entity.OpenWeatherRequestEntity
import com.despaircorp.domain.weather.entity.OpenWeatherResponseEntity
import io.ktor.http.content.MultiPartData
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.unmockkObject
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ManagerGetWeatherRequestUseCaseUnitTest {
    private val validateApiKeyUseCase: ValidateApiKeyUseCase = mockk()
    private val unwrapWeatherMultipartUseCase: UnwrapWeatherMultipartUseCase = mockk()
    private val requestWeatherApiUseCase: RequestWeatherApiUseCase = mockk()

    private val useCase = ManageGetWeatherRequestUseCase(
        validateApiKeyUseCase = validateApiKeyUseCase,
        unwrapWeatherMultipartUseCase = unwrapWeatherMultipartUseCase,
        requestWeatherApiUseCase = requestWeatherApiUseCase
    )

    @BeforeEach
    fun setUp() {
        mockkObject(WeatherSentenceGenerator)

        coEvery { validateApiKeyUseCase(API_KEY) } returns Result.success(true)
        coEvery { unwrapWeatherMultipartUseCase.invoke(any()) } returns OpenWeatherRequestEntity(
            latitude = DEFAULT_LAT,
            longitude = DEFAULT_LON,
            language = DEFAULT_LANG
        )

        coEvery { requestWeatherApiUseCase.invoke(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG) } returns Result.success(
            provideOpenWeatherResponseEntity()
        )

        every {
            WeatherSentenceGenerator.getWeatherPhrase(
                condition = DEFAULT_CONDITION,
                temp = DEFAULT_TEMPERATURE,
                lang = DEFAULT_LANG
            )
        } returns DEFAULT_WEATHER_PHRASE
    }

    @AfterEach
    fun tearDown() {
        unmockkObject(WeatherSentenceGenerator)
    }

    @Test
    fun `nominal case - api key is ok should return api response`() = runTest {
        val multipart = createMultiPartData()

        val result = useCase(API_KEY, multipart)

        assertThat(result).isEqualTo(Result.success(MonoTeoApiResponse(
            openWeatherResponseResult = provideOpenWeatherResponseEntity(),
            sentence = DEFAULT_WEATHER_PHRASE
        )))

        coVerify {
            validateApiKeyUseCase(API_KEY)
            unwrapWeatherMultipartUseCase.invoke(any())
            requestWeatherApiUseCase.invoke(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)
            WeatherSentenceGenerator.getWeatherPhrase(
                condition = DEFAULT_CONDITION,
                temp = DEFAULT_TEMPERATURE,
                lang = DEFAULT_LANG
            )
        }

        confirmVerified(
            validateApiKeyUseCase,
                    unwrapWeatherMultipartUseCase,
                    requestWeatherApiUseCase,
                    WeatherSentenceGenerator,
        )
    }

    @Test
    fun `error case - api key is ok should return api response`() = runTest {
        coEvery { requestWeatherApiUseCase.invoke(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG) } returns Result.failure(ErrorManagerException(
            ErrorManager.REQUEST_ERROR))

        val multipart = createMultiPartData()

        val result = useCase(API_KEY, multipart)

        assertThat(result)
            .isFailure()
            .isInstanceOf(ErrorManagerException::class)
            .hasMessage("Request error")

        coVerify {
            validateApiKeyUseCase(API_KEY)
            unwrapWeatherMultipartUseCase.invoke(any())
            requestWeatherApiUseCase.invoke(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)
        }

        confirmVerified(
            validateApiKeyUseCase,
            unwrapWeatherMultipartUseCase,
            requestWeatherApiUseCase,
        )
    }

    @Test
    fun `error case - api key is ok should return api null response`() = runTest {
        val multipart = createMultiPartData(longitude = null)

        coEvery { unwrapWeatherMultipartUseCase.invoke(multipart) } returns null

        val result = useCase(API_KEY, multipart)

        assertThat(result)
            .isFailure()
            .isInstanceOf(ErrorManagerException::class)
            .hasMessage("Request error")

        coVerify {
            validateApiKeyUseCase(API_KEY)
            unwrapWeatherMultipartUseCase.invoke(multipart)

        }

        confirmVerified(
            validateApiKeyUseCase,
            unwrapWeatherMultipartUseCase,

        )
    }

    @Test
    fun `error case - api key return failure`() = runTest {
        coEvery { validateApiKeyUseCase("") } returns Result.failure(ErrorManagerException(ErrorManager.NO_KEY))

        val multipart = createMultiPartData()

        val result = useCase.invoke("", multipart)

        assertThat(result)
            .isFailure()
            .isInstanceOf(ErrorManagerException::class)
            .hasMessage("No key")

        coVerify {
            validateApiKeyUseCase("")
        }

        confirmVerified(
            validateApiKeyUseCase,

        )
    }

    @Test
    fun `error case - api key return wrong key`() = runTest {
        coEvery { validateApiKeyUseCase(API_KEY) } returns Result.success(false)

        val multipart = createMultiPartData()

        val result = useCase.invoke(API_KEY, multipart)

        result.getOrElse {
            assertThat(it).isInstanceOf(ErrorManagerException::class.java).hasMessage("Wrong token")
        }

        coVerify {
            validateApiKeyUseCase(API_KEY)
        }

        confirmVerified(
            validateApiKeyUseCase,

            )
    }

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

    private fun createMultiPartData(
        latitude: String? = DEFAULT_LAT.toString(),
        longitude: String? = DEFAULT_LON.toString(),
        language: String? = DEFAULT_LANG
    ): MultiPartData {
        val parts = mutableListOf<PartData>()

        latitude?.let {
            parts.add(mockk<PartData.FormItem> {
                every { name } returns "latitude"
                every { value } returns it
                every { dispose() } returns Unit
            })
        }

        longitude?.let {
            parts.add(mockk<PartData.FormItem> {
                every { name } returns "longitude"
                every { value } returns it
                every { dispose() } returns Unit
            })
        }

        language?.let {
            parts.add(mockk<PartData.FormItem> {
                every { name } returns "language"
                every { value } returns it
                every { dispose() } returns Unit
            })
        }

        val iterator = parts.iterator()

        return mockk {
            coEvery { readPart() } answers {
                if (iterator.hasNext()) iterator.next() else null
            }
        }
    }

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
        private const val DEFAULT_CONDITION = "Rain"
        private const val DEFAULT_WEATHER_PHRASE = "DEFAULT_WEATHER_PHRASE"
    }
}