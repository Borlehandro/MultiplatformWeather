package io.decentury.mutliplatform.weatherkmm.network.weather.response

import kotlinx.serialization.Serializable

@Serializable
class CurrentWeatherData(
    val values: WeatherValues,
)
