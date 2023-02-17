package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.domain.GeocodingInteractor
import io.decentury.mutliplatform.weatherkmm.domain.GeocodingInteractorImpl
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal val domainModule = DI.Module("coreModule") {

    bindSingleton<WeatherInteractor> { WeatherInteractorImpl(instance()) }
    bindSingleton<GeocodingInteractor> { GeocodingInteractorImpl(instance()) }
}
