package io.decentury.mutliplatform.weatherkmm.android

import android.app.Application
import io.decentury.mutliplatform.weatherkmm.di.CoreInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WeatherKmmApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherKmmApp)
            modules(CoreInjector.coreModule())
        }
    }
}
