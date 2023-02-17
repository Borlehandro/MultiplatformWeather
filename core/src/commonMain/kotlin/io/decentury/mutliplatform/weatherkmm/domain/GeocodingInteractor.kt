package io.decentury.mutliplatform.weatherkmm.domain

import io.decentury.mutliplatform.weatherkmm.model.CountryWithCity
import io.decentury.mutliplatform.weatherkmm.model.Location

interface GeocodingInteractor {

    suspend fun getCountryWithCity(location: Location, locale: String): CountryWithCity?
}
