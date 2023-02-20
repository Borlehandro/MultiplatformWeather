package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.data.repository.GeocodingRepository
import io.decentury.mutliplatform.weatherkmm.data.repository.GeocodingRepositoryImpl
import io.decentury.mutliplatform.weatherkmm.data.repository.WeatherRepository
import io.decentury.mutliplatform.weatherkmm.data.repository.WeatherRepositoryImpl
import io.decentury.mutliplatform.weatherkmm.network.geocoding.GeocodingApi
import io.decentury.mutliplatform.weatherkmm.network.weather.WeatherApi
import org.koin.dsl.module

internal val dataModule = module {

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<GeocodingRepository> { GeocodingRepositoryImpl(GeocodingApi(get())) }
}

internal val apiModule = module {

    single { WeatherApi(get()) }
    single { GeocodingApi(get()) }
}
