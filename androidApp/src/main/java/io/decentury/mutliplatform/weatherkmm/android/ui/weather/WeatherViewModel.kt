package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.decentury.mutliplatform.weatherkmm.android.common.model.LoadableState
import io.decentury.mutliplatform.weatherkmm.android.common.util.classLogger
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import io.decentury.mutliplatform.weatherkmm.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.TimeZone

class WeatherViewModel(private val interactor: WeatherInteractor) : ViewModel() {

    private val log by classLogger()

    private val _state = MutableStateFlow(
        WeatherState(
            LoadableState.Initial,
            LoadableState.Initial,
        ),
    )
    val state = _state.asStateFlow()

    fun loadInitialData(latitude: Double, longitude: Double) {
        log.d("Load initial data for location. Latitude: $latitude; longitude: $longitude")
        viewModelScope.launch {
            loadCurrentWeather(latitude, longitude)
        }.invokeOnCompletion {
            viewModelScope.launch {
                loadFutureWeather(latitude, longitude)
            }
        }
    }

    private suspend fun loadCurrentWeather(latitude: Double, longitude: Double) {
        try {
            _state.update {
                it.copy(currentWeatherState = LoadableState.Loading)
            }
            _state.update {
                it.copy(
                    currentWeatherState = LoadableState.Success(
                        interactor.getCurrentWeather(
                            Location(latitude, longitude),
                            TimeZone.getDefault().id,
                        ),
                    ),
                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(currentWeatherState = LoadableState.Error(e)) }
        }
    }

    private suspend fun loadFutureWeather(latitude: Double, longitude: Double) {
        try {
            _state.update {
                it.copy(futureWeatherState = LoadableState.Loading)
            }
            _state.update { weatherState ->
                weatherState.copy(
                    futureWeatherState = LoadableState.Success(
                        interactor.getFutureWeather(
                            Location(latitude, longitude),
                            TimeZone.getDefault().id,
                        ).map {
                            WeatherState.FutureWeatherItem(
                                it.key,
                                it.value.type,
                                it.value.temperature,
                            )
                        },
                    ),
                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(futureWeatherState = LoadableState.Error(e)) }
        }
    }
}
