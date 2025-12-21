package com.despaircorp.utils

import org.slf4j.LoggerFactory

object MonoTeoLogger {
    fun debug(message: String, caller: String = Thread.currentThread().stackTrace[2].className) {
        LoggerFactory.getLogger(caller).debug(message)
    }

    fun info(message: String, caller: String = Thread.currentThread().stackTrace[2].className) {
        LoggerFactory.getLogger(caller).info(message)
    }

    fun warn(message: String, caller: String = Thread.currentThread().stackTrace[2].className) {
        LoggerFactory.getLogger(caller).warn(message)
    }

    fun error(message: String, throwable: Throwable? = null, caller: String = Thread.currentThread().stackTrace[2].className) {
        LoggerFactory.getLogger(caller).error(message, throwable)
    }
}

