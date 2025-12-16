package com.despaircorp

import com.despaircorp.di.dataModule
import com.despaircorp.di.domainModule
import com.despaircorp.di.networkModule
import com.despaircorp.di.presentationModule
import com.despaircorp.presentation.getRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import org.koin.ktor.plugin.Koin
import org.slf4j.event.Level

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CallLogging) {
        level = Level.INFO
    }

    install(Koin) {
        modules(networkModule, dataModule, domainModule, presentationModule)
    }

    install(ContentNegotiation) {
        json()
    }

    routing {
        getRoutes()
    }
}