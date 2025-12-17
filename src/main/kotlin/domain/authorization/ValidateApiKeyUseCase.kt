package com.despaircorp.domain.authorization

import com.despaircorp.domain.authorization.model.RequestApiKeyException
import com.despaircorp.domain.authorization.model.ValidateApiKeyErrorEnum

class ValidateApiKeyUseCase(
    private val repository: AuthorizationRepository
) {
    operator fun invoke(apiKey: String?): Result<Boolean> {
        if (apiKey.isNullOrBlank()) return Result.failure(RequestApiKeyException(ValidateApiKeyErrorEnum.NO_KEY))

        val isAuthorized = repository.authorizeKey(apiKey)
        return when {
            isAuthorized -> Result.success(true)
            else -> Result.failure(RequestApiKeyException(ValidateApiKeyErrorEnum.WRONG_TOKEN))
        }
    }
}