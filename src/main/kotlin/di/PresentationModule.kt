package com.despaircorp.di

import com.despaircorp.utils.MonoTeoLogger
import org.koin.dsl.module

val presentationModule = module {
    single { MonoTeoLogger }
}