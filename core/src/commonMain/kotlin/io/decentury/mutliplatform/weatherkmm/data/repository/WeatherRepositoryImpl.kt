package io.decentury.mutliplatform.weatherkmm.data.repository

import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.model.Weather
import io.decentury.mutliplatform.weatherkmm.network.weather.WeatherApi
import io.decentury.mutliplatform.weatherkmm.network.weather.response.WeatherValues
import kotlin.math.roundToInt
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class WeatherRepositoryImpl(
    private val api: WeatherApi,
) : WeatherRepository {

    override suspend fun current(location: Location) =
        api.current(location.latitude, location.longitude).data.values.toDomain()

    override suspend fun future(location: Location, timezone: String): Map<String, Weather> =
        api.future(location.latitude, location.longitude, timezone).data.timelines
            .getOrNull(0)
            ?.intervals
            .orEmpty()
            .associateBy {
                it.startTime
                    .toInstant()
                    .toLocalDateTime(TimeZone.of(timezone))
                    .run {
                        "${time.hour}:${time.minute.toString().padStart(2, '0')}"
                    }
            }
            .map {
                it.key to it.value.values.toDomain()
            }
            .toMap()

    private fun WeatherValues.toDomain() =
        Weather(
            type = when (weatherCode) {
                1000, 1100 -> Weather.Type.CLEAR
                1101, 2100 -> Weather.Type.PARTY_CLOUDY
                1001, 1102, 2000 -> Weather.Type.CLOUDY
                in 4_000..8_000 -> Weather.Type.CLOUDY_RAIN
                else -> throw IllegalArgumentException(
                    "Unknown weather type with code: $weatherCode",
                )
            },
            temperature = temperature.roundToInt(),
            rainFall = (rainIntensity * 10).roundToInt(),
            windSpeed = (windSpeed * 3.6).roundToInt(),
            humidity = humidity.roundToInt(),
        )
}
