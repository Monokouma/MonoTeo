package com.despaircorp.presentation

import io.ktor.http.ContentType
import io.ktor.server.application.call
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.getRoutes() {

    get("/health") {
        call.respondText("resource.readBytes()", ContentType.Text.Plain)
    }
}