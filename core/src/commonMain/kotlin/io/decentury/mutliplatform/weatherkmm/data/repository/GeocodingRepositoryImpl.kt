package io.decentury.mutliplatform.weatherkmm.data.repository

import io.decentury.mutliplatform.weatherkmm.model.CountryWithCity
import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.network.geocoding.GeocodingApi

class GeocodingRepositoryImpl(
    private val geocodingApi: GeocodingApi,
) : GeocodingRepository {

    override suspend fun getCountryWithCity(location: Location, locale: String) =
        geocodingApi.countryWithCity(location.latitude, location.longitude, locale)?.let {
            CountryWithCity(
                country = it.country,
                city = it.city,
            )
        }
}
