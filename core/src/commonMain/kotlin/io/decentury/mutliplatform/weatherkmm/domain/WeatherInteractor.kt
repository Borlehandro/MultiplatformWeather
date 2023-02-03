package io.decentury.mutliplatform.weatherkmm.domain

import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.model.Weather

interface WeatherInteractor {

    suspend fun getCurrentWeather(location: Location, timeZone: String): Weather

    suspend fun getFutureWeather(location: Location, timeZone: String): Map<String, Weather>
}
