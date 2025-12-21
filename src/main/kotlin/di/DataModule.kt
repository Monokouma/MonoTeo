package com.despaircorp.di

import com.despaircorp.data.authorization.AuthorizationRepositoryImpl
import com.despaircorp.data.factory.DatabaseFactory
import com.despaircorp.data.weather.OpenWeatherRepositoryImpl
import com.despaircorp.domain.authorization.AuthorizationRepository
import com.despaircorp.domain.weather.OpenWeatherRepository
import com.despaircorp.utils.MonoTeoLogger
import io.github.cdimascio.dotenv.dotenv
import org.koin.dsl.module

val dataModule = module {
    single {
        dotenv {
            ignoreIfMissing = true
            systemProperties = true
        }
    }

    single { MonoTeoLogger }

    single { DatabaseFactory.connect() }

    single<AuthorizationRepository> {
        AuthorizationRepositoryImpl(
            dotenv = get(),
        )
    }

    single<OpenWeatherRepository> {
        OpenWeatherRepositoryImpl(
            httpClient = get(),
            dotenv = get(),
        )
    }
}