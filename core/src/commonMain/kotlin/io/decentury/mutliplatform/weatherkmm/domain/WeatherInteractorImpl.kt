package io.decentury.mutliplatform.weatherkmm.domain

import io.decentury.mutliplatform.weatherkmm.data.repository.WeatherRepository
import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.model.Weather

class WeatherInteractorImpl(
    private val repository: WeatherRepository,
) : WeatherInteractor {

    override suspend fun getCurrentWeather(location: Location, timeZone: String) =
        repository.current(location)

    override suspend fun getFutureWeather(
        location: Location,
        timeZone: String,
    ): Map<String, Weather> = repository.future(location, timeZone)
}
