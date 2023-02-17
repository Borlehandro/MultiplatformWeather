package io.decentury.mutliplatform.weatherkmm.android.di

import io.decentury.mutliplatform.weatherkmm.android.ui.weather.WeatherViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val viewModelsModule = DI.Module("androidViewModels") {
    bindProvider { WeatherViewModel(instance(), instance()) }
}
