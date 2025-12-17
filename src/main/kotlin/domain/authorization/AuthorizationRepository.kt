package com.despaircorp.domain.authorization

interface AuthorizationRepository {
    fun authorizeKey(key: String): Boolean
}