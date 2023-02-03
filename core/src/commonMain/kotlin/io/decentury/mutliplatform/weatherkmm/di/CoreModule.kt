package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton

internal val domainModule = DI.Module("coreModule") {

    bindSingleton<WeatherInteractor> { WeatherInteractorImpl() }
}
