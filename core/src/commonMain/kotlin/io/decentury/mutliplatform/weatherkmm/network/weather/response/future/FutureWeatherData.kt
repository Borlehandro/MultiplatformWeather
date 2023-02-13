package io.decentury.mutliplatform.weatherkmm.network.weather.response.future

import kotlinx.serialization.Serializable

@Serializable
class FutureWeatherData(
    val timelines: List<FutureWeatherTimeline>,
)
