package com.despaircorp.domain.authorization

import com.despaircorp.domain.error_manager.ErrorManager
import com.despaircorp.domain.error_manager.ErrorManagerException


class ValidateApiKeyUseCase(
    private val repository: AuthorizationRepository
) {
    operator fun invoke(apiKey: String?): Result<Boolean> {
        if (apiKey.isNullOrBlank()) return Result.failure(ErrorManagerException(ErrorManager.NO_KEY))

        return Result.success(repository.authorizeKey(apiKey))
    }
}