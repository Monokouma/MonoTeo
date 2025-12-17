package data.authorization

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.despaircorp.data.authorization.AuthorizationRepositoryImpl
import io.github.cdimascio.dotenv.Dotenv
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthorizationRepositoryImplUnitTest {

    private lateinit var authorizationRepository: AuthorizationRepositoryImpl
    private val dotenv: Dotenv = mockk()

    @BeforeEach
    fun setup() {

        authorizationRepository = AuthorizationRepositoryImpl(dotenv)
    }

    @Test
    fun `nominal case - authorizeKey return true`() = runTest {
        every { dotenv["API_KEY"] } returns API_KEY

        val result = authorizationRepository.authorizeKey(API_KEY)
        assertThat(result).isTrue()

        coVerify {
            dotenv["API_KEY"]
        }
        confirmVerified(dotenv)
    }

    @Test
    fun `error case - authorizeKey return true`() = runTest {
        every { dotenv["API_KEY"] } returns API_KEY

        val result = authorizationRepository.authorizeKey(WRONG_KEY)
        assertThat(result).isFalse()

        coVerify {
            dotenv["API_KEY"]
        }
        confirmVerified(dotenv)
    }


    companion object {
        private const val API_KEY = "API_KEY"
        private const val WRONG_KEY = "WRONG_KEY"
    }
}