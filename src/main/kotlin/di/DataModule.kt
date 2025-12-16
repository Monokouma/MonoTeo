package com.despaircorp.di

import com.despaircorp.data.factory.DatabaseFactory
import org.koin.dsl.module

val dataModule = module {
    single { DatabaseFactory.connect() }
}