package io.decentury.mutliplatform.weatherkmm.di

import org.koin.dsl.module

// This module should be visible externally for manual using
val coreModule = module {
    includes(
        networkModule,
        apiModule,
        dataModule,
        domainModule,
        viewModelsModule,
    )
}
