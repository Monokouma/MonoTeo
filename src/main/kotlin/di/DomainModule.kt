package com.despaircorp.di

import com.despaircorp.domain.authorization.ValidateApiKeyUseCase
import com.despaircorp.domain.weather.ManageGetWeatherRequestUseCase
import com.despaircorp.domain.weather.RequestWeatherApiUseCase
import com.despaircorp.domain.weather.UnwrapWeatherMultipartUseCase
import com.despaircorp.utils.MonoTeoLogger
import org.koin.dsl.module

val domainModule = module {
    single { MonoTeoLogger }

    factory { ValidateApiKeyUseCase(repository = get()) }
    factory { ManageGetWeatherRequestUseCase(
        validateApiKeyUseCase = get(),
        unwrapWeatherMultipartUseCase = get(),
        requestWeatherApiUseCase = get()
    ) }
    factory { UnwrapWeatherMultipartUseCase() }

    factory { RequestWeatherApiUseCase(openWeatherRepository = get()) }
}