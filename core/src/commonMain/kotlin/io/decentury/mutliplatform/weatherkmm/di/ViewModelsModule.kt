package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.viewModel.WeatherViewModel
import org.koin.dsl.module

internal val viewModelsModule = module {
    factory { WeatherViewModel(get(), get()) }
}
