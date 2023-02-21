package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.viewModel.WeatherViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.native.concurrent.ThreadLocal

// TODO: This object use service locator antipattern and has bad scalability.
//  Think about better architecture solution.
@ThreadLocal
object CoreInjector : KoinComponent {

    // Direct access to the core dependencies. Use it in the iOS to get instances.
    fun provideWeatherViewModel() = get<WeatherViewModel>()
}
