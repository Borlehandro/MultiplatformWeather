package io.decentury.mutliplatform.weatherkmm.network.weather

import io.decentury.mutliplatform.weatherkmm.network.weather.response.CurrentWeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path

class WeatherApi(
    private val client: HttpClient,
) {

    companion object {

        private const val PATH_WEATHER = "/weather"
        private const val PATH_CURRENT = "$PATH_WEATHER/realtime"
        private const val LOCATION_KEY = "location"
    }

    suspend fun current(latitude: Double, longitude: Double): CurrentWeatherResponse =
        client.get {
            url {
                path(PATH_CURRENT)
                parameter(LOCATION_KEY, "$latitude, $longitude")
            }
        }.body()
}
