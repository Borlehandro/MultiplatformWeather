package io.decentury.mutliplatform.weatherkmm.data.repository

import io.decentury.mutliplatform.weatherkmm.model.CountryWithCity
import io.decentury.mutliplatform.weatherkmm.model.Location

interface GeocodingRepository {

    suspend fun getCountryWithCity(location: Location, locale: String): CountryWithCity?
}
