package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.domain.GeocodingInteractor
import io.decentury.mutliplatform.weatherkmm.domain.GeocodingInteractorImpl
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractorImpl
import org.koin.dsl.module

internal val domainModule = module {

    single<WeatherInteractor> {
        WeatherInteractorImpl(get())
    }
    single<GeocodingInteractor> {
        GeocodingInteractorImpl(get())
    }
}
