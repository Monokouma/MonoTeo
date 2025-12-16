package com.despaircorp.data.factory

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {
    val dotenv = dotenv()

    fun connect() {
        val databaseUrl = dotenv["DATABASE_URL"]

        val config = HikariConfig().apply {
            jdbcUrl = databaseUrl.replace("postgresql://", "jdbc:postgresql://")
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 3
        }

        Database.connect(HikariDataSource(config))
    }
}