package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.decentury.mutliplatform.weatherkmm.android.common.LoadableState
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import io.decentury.mutliplatform.weatherkmm.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.TimeZone

class WeatherViewModel(private val interactor: WeatherInteractor) : ViewModel() {

    private val _state = MutableStateFlow(
        WeatherState(
            LoadableState.Initial,
            LoadableState.Initial
        )
    )
    val state = _state.asStateFlow()

    // TODO: Call loadInitialData when geolocation permission is granted
    init {
        loadInitialData()
    }

    // TODO: Add geolocation to params
    private fun loadInitialData() {
        viewModelScope.launch {
            loadCurrentWeather()
        }.invokeOnCompletion {
            viewModelScope.launch {
                loadFutureWeather()
            }
        }
    }

    private suspend fun loadCurrentWeather() {
        try {
            _state.update {
                it.copy(currentWeatherState = LoadableState.Loading)
            }
            _state.update {
                it.copy(
                    currentWeatherState = LoadableState.Success(
                        interactor.getCurrentWeather(
                            // TODO: Use real geolocation
                            Location(58.0, 58.0),
                            TimeZone.getDefault().id
                        )
                    )
                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(currentWeatherState = LoadableState.Error(e)) }
        }
    }

    private suspend fun loadFutureWeather() {
        try {
            _state.update {
                it.copy(futureWeatherState = LoadableState.Loading)
            }
            _state.update { weatherState ->
                weatherState.copy(
                    futureWeatherState = LoadableState.Success(
                        interactor.getFutureWeather(
                            // TODO: Use real geolocation
                            Location(58.0, 58.0),
                            TimeZone.getDefault().id
                        ).map {
                            WeatherState.FutureWeatherItem(
                                it.key,
                                it.value.type,
                                it.value.temperature
                            )
                        }
                    )
                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(futureWeatherState = LoadableState.Error(e)) }
        }
    }
}
