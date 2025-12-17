package domain.authorization

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import com.despaircorp.data.authorization.AuthorizationRepositoryImpl
import com.despaircorp.domain.authorization.AuthorizationRepository
import com.despaircorp.domain.authorization.ValidateApiKeyUseCase
import com.despaircorp.domain.authorization.model.RequestApiKeyException
import com.despaircorp.domain.authorization.model.ValidateApiKeyErrorEnum
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateApiKeyUseCaseUnitTest {

    private val repository: AuthorizationRepository = mockk()

    private val useCase = ValidateApiKeyUseCase(
        repository = repository
    )

    @BeforeEach
    fun setup() {

    }

    @Test
    fun `nominal case - validate api key return true`() {
        every { repository.authorizeKey(API_KEY) } returns true
        val result = useCase.invoke(API_KEY)

        assertThat(result).isEqualTo(Result.success(true))

        verify {
            repository.authorizeKey(API_KEY)
        }

        confirmVerified(repository)
    }

    @Test
    fun `error case - validate api key return false`() {
        every { repository.authorizeKey(API_KEY) } returns false
        val result = useCase.invoke(API_KEY)

        assertThat(result)
            .isFailure()
            .isInstanceOf(RequestApiKeyException::class)
            .hasMessage("Wrong token")

        verify {
            repository.authorizeKey(API_KEY)
        }

        confirmVerified(repository)
    }

    @Test
    fun `error case - null api key`() {
        val result = useCase.invoke(null)

        assertThat(result)
            .isFailure()
            .isInstanceOf(RequestApiKeyException::class)
            .hasMessage("No key")
    }

    @Test
    fun `error case - blank api key`() {
        val result = useCase.invoke("")

        assertThat(result)
            .isFailure()
            .isInstanceOf(RequestApiKeyException::class)
            .hasMessage("No key")
    }


    companion object {
        private const val API_KEY = "API_KEY"
        private const val WRONG_KEY = "WRONG_KEY"
    }
}