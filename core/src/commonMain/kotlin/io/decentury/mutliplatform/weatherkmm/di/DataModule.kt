package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.data.repository.GeocodingRepository
import io.decentury.mutliplatform.weatherkmm.data.repository.GeocodingRepositoryImpl
import io.decentury.mutliplatform.weatherkmm.data.repository.WeatherRepository
import io.decentury.mutliplatform.weatherkmm.data.repository.WeatherRepositoryImpl
import io.decentury.mutliplatform.weatherkmm.network.geocoding.GeocodingApi
import io.decentury.mutliplatform.weatherkmm.network.weather.WeatherApi
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal val dataModule = DI.Module("dataModule") {

    bindSingleton<WeatherRepository> { WeatherRepositoryImpl(instance()) }
    bindSingleton<GeocodingRepository> { GeocodingRepositoryImpl(instance()) }
}

internal val apiModule = DI.Module("apiModule") {

    bindSingleton { WeatherApi(instance()) }
    bindSingleton { GeocodingApi(instance()) }
}
