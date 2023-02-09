package io.decentury.mutliplatform.weatherkmm.data.repository

import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.model.Weather
import io.decentury.mutliplatform.weatherkmm.network.weather.WeatherApi
import io.decentury.mutliplatform.weatherkmm.network.weather.response.CurrentWeatherResponse
import kotlin.math.roundToInt

class WeatherRepositoryImpl(
    private val api: WeatherApi,
) : WeatherRepository {

    override suspend fun current(location: Location) =
        api.current(location.latitude, location.longitude).toDomain()

    override suspend fun future(location: Location): Map<String, Weather> {
        TODO("Not yet implemented")
    }

    private fun CurrentWeatherResponse.toDomain() =
        with(data.values) {
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
}
