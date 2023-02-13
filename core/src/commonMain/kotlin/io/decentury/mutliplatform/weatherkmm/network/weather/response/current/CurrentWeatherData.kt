package io.decentury.mutliplatform.weatherkmm.network.weather.response.current

import io.decentury.mutliplatform.weatherkmm.network.weather.response.WeatherValues
import kotlinx.serialization.Serializable

@Serializable
class CurrentWeatherData(
    val values: WeatherValues,
)
