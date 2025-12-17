package com.despaircorp.domain.authorization.model

import io.ktor.http.HttpStatusCode

enum class ValidateApiKeyErrorEnum(
    val message: String,
    val statusCode: HttpStatusCode
) {
    WRONG_TOKEN("Wrong token", HttpStatusCode.Unauthorized),
    NO_KEY("No key", HttpStatusCode.NotFound),
}

class RequestApiKeyException(val error: ValidateApiKeyErrorEnum) : Exception(error.message) {
    val statusCode: HttpStatusCode
        get() = error.statusCode
}