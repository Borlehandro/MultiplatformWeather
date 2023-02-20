package io.decentury.mutliplatform.weatherkmm.di

import io.decentury.mutliplatform.weatherkmm.viewModel.WeatherViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val viewModelsModule = DI.Module("androidViewModels") {
    bindProvider { WeatherViewModel(instance(), instance()) }
}