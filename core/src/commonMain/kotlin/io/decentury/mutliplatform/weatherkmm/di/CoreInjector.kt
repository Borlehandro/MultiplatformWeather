package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import org.kodein.di.DI
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

// TODO: This object use service locator antipattern and has bad scalability.
//  Think about better architecture solution.
@ThreadLocal
object CoreInjector {

    val coreModule = DI.Module("core") {
        importAll(domainModule)
    }

    val coreContainer = DI.lazy {
        importAll(coreModule)
    }

    // Direct access to the core dependencies. Use it in the iOS to get instances.
    fun provideWeatherInteractor() = coreContainer.direct.instance<WeatherInteractor>()
}
