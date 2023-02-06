package io.decentury.mutliplatform.weatherkmm.android

import android.app.Application
import io.decentury.mutliplatform.weatherkmm.android.di.viewModelsModule
import io.decentury.mutliplatform.weatherkmm.di.CoreInjector
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule

class WeatherKmmApp : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@WeatherKmmApp))
        import(CoreInjector.coreModule)
        import(viewModelsModule)
    }
}
