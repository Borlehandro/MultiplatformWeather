package io.decentury.mutliplatform.weatherkmm.data.repository

import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.model.Weather

interface WeatherRepository {

    suspend fun current(location: Location): Weather
    suspend fun future(location: Location): Map<String, Weather>
}
