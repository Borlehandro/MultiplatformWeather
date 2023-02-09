package io.decentury.mutliplatform.weatherkmm.network.weather.response

import kotlinx.serialization.Serializable

@Serializable
class CurrentWeatherResponse(
    val data: CurrentWeatherData,
)
