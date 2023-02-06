package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import androidx.lifecycle.ViewModel
import io.decentury.mutliplatform.weatherkmm.android.common.LoadableState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// TODO: Inject interactor with kodein
class WeatherViewModel(
    // private val interactor: WeatherInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(
        WeatherState(
            LoadableState.Initial,
            LoadableState.Initial
        )
    )
    val state = _state.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // TODO: Implement
    }
}
