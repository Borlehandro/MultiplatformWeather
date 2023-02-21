package io.decentury.mutliplatform.weatherkmm.di

import org.koin.core.context.startKoin

@Suppress("Unused")
object DiSetupHelper {

    fun initDi() {
        startKoin {
            modules(coreModule)
        }
    }
}
