package com.despaircorp.di

import com.despaircorp.domain.authorization.ValidateApiKeyUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { ValidateApiKeyUseCase(get()) }
}