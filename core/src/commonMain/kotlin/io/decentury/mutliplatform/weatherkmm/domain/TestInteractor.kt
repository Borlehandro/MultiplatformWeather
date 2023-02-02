package io.decentury.mutliplatform.weatherkmm.domain

import io.decentury.mutliplatform.weatherkmm.model.TestModel
import kotlinx.coroutines.delay

// Test only
class TestInteractor {

    suspend fun getTestModel(): TestModel {
        delay(2_000)
        return TestModel(1, "Test 1")
    }
}