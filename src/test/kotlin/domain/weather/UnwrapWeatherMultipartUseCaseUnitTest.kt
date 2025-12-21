package domain.weather

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.despaircorp.domain.weather.UnwrapWeatherMultipartUseCase
import com.despaircorp.domain.weather.entity.OpenWeatherRequestEntity
import io.ktor.http.content.MultiPartData
import io.ktor.http.content.PartData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UnwrapWeatherMultipartUseCaseUnitTest {

    private lateinit var useCase: UnwrapWeatherMultipartUseCase

    @BeforeEach
    fun setUp() {
        useCase = UnwrapWeatherMultipartUseCase()
    }

    private fun createMultiPartData(
        latitude: String? = DEFAULT_LAT,
        longitude: String? = DEFAULT_LON,
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

    @Test
    fun `nominal case - returns OpenWeatherRequestEntity`() = runTest {
        val multipart = createMultiPartData()

        val result = useCase(multipart)

        assertThat(result).isEqualTo(
            OpenWeatherRequestEntity(
                latitude = 48.8566,
                longitude = 2.3522,
                language = "fr"
            )
        )
    }

    @Test
    fun `error case - returns null when latitude is missing`() = runTest {
        val multipart = createMultiPartData(latitude = null)

        val result = useCase(multipart)

        assertThat(result).isNull()
    }

    @Test
    fun `error case - returns null when longitude is missing`() = runTest {
        val multipart = createMultiPartData(longitude = null)

        val result = useCase(multipart)

        assertThat(result).isNull()
    }

    @Test
    fun `error case - returns null when language is missing`() = runTest {
        val multipart = createMultiPartData(language = null)

        val result = useCase(multipart)

        assertThat(result).isNull()
    }

    @Test
    fun `error case - returns null when latitude is not a valid double`() = runTest {
        val multipart = createMultiPartData(latitude = "invalid")

        val result = useCase(multipart)

        assertThat(result).isNull()
    }

    @Test
    fun `error case - returns null when longitude is not a valid double`() = runTest {
        val multipart = createMultiPartData(longitude = "invalid")

        val result = useCase(multipart)

        assertThat(result).isNull()
    }

    @Test
    fun `edge case - returns entity with negative coordinates`() = runTest {
        val multipart = createMultiPartData(latitude = "-33.8688", longitude = "-151.2093")

        val result = useCase(multipart)

        assertThat(result).isEqualTo(
            OpenWeatherRequestEntity(
                latitude = -33.8688,
                longitude = -151.2093,
                language = "fr"
            )
        )
    }

    @Test
    fun `edge case - returns entity with zero coordinates`() = runTest {
        val multipart = createMultiPartData(latitude = "0.0", longitude = "0.0")

        val result = useCase(multipart)

        assertThat(result).isEqualTo(
            OpenWeatherRequestEntity(
                latitude = 0.0,
                longitude = 0.0,
                language = "fr"
            )
        )
    }

    companion object {
        private const val DEFAULT_LAT = "48.8566"
        private const val DEFAULT_LON = "2.3522"
        private const val DEFAULT_LANG = "fr"
    }
}