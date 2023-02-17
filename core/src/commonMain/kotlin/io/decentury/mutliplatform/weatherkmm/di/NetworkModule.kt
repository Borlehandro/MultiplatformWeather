package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.network.HttpEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton

private const val CONNECTION_TIMEOUT_MILLIS = 10_000L
private const val REQUEST_TIMEOUT_MILLIS = 30_000L

private const val WEATHER_HOST = "api.tomorrow.io"
private const val BASE_HEATHER_HOST_URL = "https://$WEATHER_HOST/v4/"

internal val networkModule = DI.Module("networkModule") {

    bindSingleton {
        HttpClient(HttpEngineFactory().createEngine()) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(DefaultRequest)

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    },
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = CONNECTION_TIMEOUT_MILLIS
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            }

            defaultRequest {
                url(BASE_HEATHER_HOST_URL)
                header("Content-Type", "application/json; charset=UTF-8")
            }
        }.apply {
            // Add API key to each request with interceptor
            plugin(HttpSend).intercept { request ->
                execute(
                    request.apply {
                        // Intercept only weather requests
                        if (request.url.host == WEATHER_HOST) {
                            // TODO: Move key to local storage
                            parameter("apikey", "UNHDFTpeSz6RWKFEJNyiLTJiOghMGMVu")
                            parameter("units", "metric")
                        }
                    },
                )
            }
        }
    }
}
