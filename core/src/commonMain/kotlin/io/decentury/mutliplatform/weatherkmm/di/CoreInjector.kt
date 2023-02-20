package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.viewModel.WeatherViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.native.concurrent.ThreadLocal

// TODO: This object use service locator antipattern and has bad scalability.
//  Think about better architecture solution.
@ThreadLocal
object CoreInjector : KoinComponent {

    fun coreModule() = listOf(networkModule, apiModule, dataModule, domainModule, viewModelsModule)

    // Direct access to the core dependencies. Use it in the iOS to get instances.
    // TODO: Check behavior

    private val viewModel: WeatherViewModel by inject()
    fun provideWeatherViewModel() = viewModel
}
