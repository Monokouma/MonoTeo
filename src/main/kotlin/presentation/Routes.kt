package com.despaircorp.presentation

import com.despaircorp.domain.authorization.ValidateApiKeyUseCase
import com.despaircorp.domain.authorization.model.RequestApiKeyException
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.header
import io.ktor.server.response.respond
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.getRoutes() {
    val validateApiKeyUseCase: ValidateApiKeyUseCase by inject()


    get("/health") {
        call.respondText("OK", ContentType.Text.Plain)
    }

    post("/get_weather") {
        val token = call.request.header("Authorization")

        validateApiKeyUseCase(token)
            .onSuccess { response ->
                call.respond(HttpStatusCode.OK)
            }.onFailure { error ->
                when (error) {
                    is RequestApiKeyException -> call.respond(error.error.statusCode, error.error.message)
                    else -> call.respond(HttpStatusCode.InternalServerError, "Unknown error")
                }
        }
    }
}