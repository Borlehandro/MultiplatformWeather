package io.decentury.mutliplatform.weatherkmm.domain

import io.decentury.mutliplatform.weatherkmm.data.repository.GeocodingRepository
import io.decentury.mutliplatform.weatherkmm.model.Location

class GeocodingInteractorImpl(private val repository: GeocodingRepository) : GeocodingInteractor {

    override suspend fun getCountryWithCity(location: Location, locale: String) =
        repository.getCountryWithCity(location, locale)
}
