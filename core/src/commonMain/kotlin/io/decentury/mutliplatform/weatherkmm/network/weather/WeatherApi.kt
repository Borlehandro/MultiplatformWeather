package io.decentury.mutliplatform.weatherkmm.network.weather

import io.decentury.mutliplatform.weatherkmm.network.weather.response.current.CurrentWeatherResponse
import io.decentury.mutliplatform.weatherkmm.network.weather.response.future.FutureWeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path

class WeatherApi(
    private val client: HttpClient,
) {

    companion object {

        private const val PATH_CURRENT = "/weather/realtime"
        private const val PATH_FUTURE = "/timelines"

        private const val LOCATION_KEY = "location"

        private const val HOURS_TO_FUTURE = 24
    }

    suspend fun current(
        latitude: Double,
        longitude: Double,
    ): CurrentWeatherResponse =
        client.get {
            url {
                path(PATH_CURRENT)
                parameter("fields", "core")
                parameter(LOCATION_KEY, "$latitude, $longitude")
            }
        }.body()

    suspend fun future(
        latitude: Double,
        longitude: Double,
        timezoneName: String,
    ): FutureWeatherResponse =
        client.get {
            url {
                path(PATH_FUTURE)
                parameter(LOCATION_KEY, "$latitude, $longitude")
                parameter("timesteps", "1h")
                parameter("timezone", timezoneName)

                parameter("fields", "weatherCode")
                parameter("fields", "temperature")
                parameter("fields", "windSpeed")
                parameter("fields", "humidity")
                parameter("fields", "rainIntensity")

                parameter("endTime", "nowPlus${HOURS_TO_FUTURE}h")
            }
        }.body()
}
