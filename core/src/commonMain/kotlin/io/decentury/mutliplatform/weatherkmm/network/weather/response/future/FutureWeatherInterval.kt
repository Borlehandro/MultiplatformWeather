package io.decentury.mutliplatform.weatherkmm.network.weather.response.future

import io.decentury.mutliplatform.weatherkmm.network.weather.response.WeatherValues
import kotlinx.serialization.Serializable

@Serializable
class FutureWeatherInterval(
    val startTime: String,
    val values: WeatherValues
)
