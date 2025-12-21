package data.weather

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.despaircorp.data.weather.OpenWeatherRepositoryImpl
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OpenWeatherRepositoryImplUnitTest {
    private val dotenv: Dotenv = mockk()

    @BeforeEach
    fun setUp() {
        every { dotenv["OW_KEY"] } returns OW_KEY
    }

    private fun createMockClient(responseJson: String, statusCode: HttpStatusCode = HttpStatusCode.OK): HttpClient {
        return HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond(
                        content = responseJson,
                        status = statusCode,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(HttpTimeout)
        }
    }

    @Test
    fun `nominal case - getWeather returns weather`() = runTest {
        val responseJson = """
            {
                "name": "Paris",
                "main": {
                    "temp": 18.5,
                    "feels_like": 17.2,
                    "humidity": 65,
                    "pressure": 1013
                },
                "weather": [{
                    "description": "ciel dégagé",
                    "main": "Clear",
                    "icon": "01d"
                }],
                "wind": { "speed": 3.5 },
                "visibility": 10000,
                "sys": {
                    "country": "FR",
                    "sunrise": 1766301365,
                    "sunset": 1766332884
                }
            }
        """.trimIndent()

        val httpClient = createMockClient(responseJson)
        val repository = OpenWeatherRepositoryImpl(httpClient, dotenv)

        val result = repository.getWeather(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)

        assertThat(result?.city).isEqualTo("Paris")
        assertThat(result?.country).isEqualTo("FR")
        assertThat(result?.temperature).isEqualTo(18.5)
        assertThat(result?.feelsLike).isEqualTo(17.2)
        assertThat(result?.humidity).isEqualTo(65)
        assertThat(result?.description).isEqualTo("ciel dégagé")
        assertThat(result?.icon).isEqualTo("https://openweathermap.org/img/wn/01d@2x.png")
        assertThat(result?.windSpeed).isEqualTo(3.5)
        assertThat(result?.pressure).isEqualTo(1013)
        assertThat(result?.visibility).isEqualTo(10000)
        assertThat(result?.condition).isEqualTo("Clear")

        coVerify {
            dotenv["OW_KEY"]
        }

        confirmVerified(dotenv)
    }

    @Test
    fun `error case - getWeather returns null on error`() = runTest {
        val httpClient = createMockClient("", HttpStatusCode.InternalServerError)
        val repository = OpenWeatherRepositoryImpl(httpClient, dotenv)

        val result = repository.getWeather(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)

        assertThat(result).isNull()

        coVerify {
            dotenv["OW_KEY"]
        }

        confirmVerified(dotenv)
    }

    @Test
    fun `error case - getWeather returns null on invalid json`() = runTest {
        val httpClient = createMockClient("invalid json")
        val repository = OpenWeatherRepositoryImpl(httpClient, dotenv)

        val result = repository.getWeather(DEFAULT_LAT, DEFAULT_LON, DEFAULT_LANG)

        assertThat(result).isNull()

        coVerify {
            dotenv["OW_KEY"]
        }

        confirmVerified(dotenv)
    }

    companion object {
        private const val OW_KEY = "fake_api_key"
        private const val DEFAULT_LAT = 48.8566
        private const val DEFAULT_LON = 2.3522
        private const val DEFAULT_LANG = "fr"
    }
}