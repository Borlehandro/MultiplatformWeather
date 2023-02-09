package io.decentury.mutliplatform.weatherkmm.network.weather.response

import kotlinx.serialization.Serializable

@Serializable
class WeatherValues(
    val weatherCode: Int,
    val temperature: Double,
    val windSpeed: Double,
    val humidity: Double,
    val rainIntensity: Double,
)
