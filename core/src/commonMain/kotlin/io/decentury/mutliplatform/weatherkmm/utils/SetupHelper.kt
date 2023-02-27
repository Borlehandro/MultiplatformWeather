package io.decentury.mutliplatform.weatherkmm.utils

import io.decentury.mutliplatform.weatherkmm.di.coreModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

@Suppress("Unused")
object SetupHelper {

    fun setup() {
        startKoin {
            modules(coreModule)
            Napier.base(DebugAntilog())
        }
    }
}
