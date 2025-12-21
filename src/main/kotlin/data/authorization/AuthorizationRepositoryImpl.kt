package com.despaircorp.data.authorization

import com.despaircorp.data.factory.DatabaseFactory
import com.despaircorp.domain.authorization.AuthorizationRepository
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv

class AuthorizationRepositoryImpl(
    private val dotenv: Dotenv
): AuthorizationRepository {

    override fun authorizeKey(key: String): Boolean = key == dotenv["API_KEY"]

}