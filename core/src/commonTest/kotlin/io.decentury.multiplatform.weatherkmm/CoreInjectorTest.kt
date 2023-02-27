package io.decentury.multiplatform.weatherkmm

import io.decentury.mutliplatform.weatherkmm.di.CoreInjector
import io.decentury.mutliplatform.weatherkmm.utils.SetupHelper
import org.koin.test.KoinTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotEquals

class CoreInjectorTest : KoinTest {

    @BeforeTest
    fun setup() {
        SetupHelper.setup()
    }

    @Test
    fun testViewModelInjection() {
        val firstViewModel = CoreInjector.provideWeatherViewModel()
        val secondViewModel = CoreInjector.provideWeatherViewModel()

        assertNotEquals(firstViewModel, secondViewModel)
    }
}
