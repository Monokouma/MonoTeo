package com.despaircorp.di

import com.despaircorp.data.authorization.AuthorizationRepositoryImpl
import com.despaircorp.data.factory.DatabaseFactory
import com.despaircorp.domain.authorization.AuthorizationRepository
import io.github.cdimascio.dotenv.dotenv
import org.koin.dsl.module

val dataModule = module {
    val dotenv = dotenv()
    single { DatabaseFactory.connect() }

    single<AuthorizationRepository> {
        AuthorizationRepositoryImpl(
            dotenv = dotenv,
        )
    }


}