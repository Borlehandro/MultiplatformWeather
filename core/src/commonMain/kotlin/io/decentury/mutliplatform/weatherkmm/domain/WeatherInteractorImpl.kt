package io.decentury.mutliplatform.weatherkmm.domain

import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.model.Weather
import kotlinx.coroutines.delay

class WeatherInteractorImpl : WeatherInteractor {

    override suspend fun getCurrentWeather(location: Location, timeZone: String): Weather {
        delay(2000)
        return Weather(
            type = Weather.Type.CLEAR,
            temperature = -15,
            rainFall = 7,
            windSpeed = 19,
            humidity = 64
        )
    }

    override suspend fun getFutureWeather(
        location: Location,
        timeZone: String
    ): Map<String, Weather> {
        delay(2000)
        return mapOf(
            "13:00" to Weather(
                type = Weather.Type.CLEAR,
                temperature = -15,
                rainFall = 7,
                windSpeed = 19,
                humidity = 64
            ),
            "14:00" to Weather(
                type = Weather.Type.CLOUDY,
                temperature = -11,
                rainFall = 7,
                windSpeed = 19,
                humidity = 65
            ),
            "15:00" to Weather(
                type = Weather.Type.PARTY_CLOUDY,
                temperature = -13,
                rainFall = 7,
                windSpeed = 19,
                humidity = 64
            ),
            "16:00" to Weather(
                type = Weather.Type.CLOUDY,
                temperature = -12,
                rainFall = 7,
                windSpeed = 19,
                humidity = 65
            ),
            "17:00" to Weather(
                type = Weather.Type.CLEAR,
                temperature = -17,
                rainFall = 7,
                windSpeed = 19,
                humidity = 64
            ),
            "18:00" to Weather(
                type = Weather.Type.CLOUDY_RAIN,
                temperature = -16,
                rainFall = 7,
                windSpeed = 19,
                humidity = 64
            ),
            "19:00" to Weather(
                type = Weather.Type.PARTY_CLOUDY_RAIN,
                temperature = -14,
                rainFall = 7,
                windSpeed = 19,
                humidity = 64
            )
        )
    }
}
