package io.decentury.mutliplatform.weatherkmm.network.weather.response.future

import kotlinx.serialization.Serializable

@Serializable
class FutureWeatherTimeline(
    val intervals: List<FutureWeatherInterval>,
)
