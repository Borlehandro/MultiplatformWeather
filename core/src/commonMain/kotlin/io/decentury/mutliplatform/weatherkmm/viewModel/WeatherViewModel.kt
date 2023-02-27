package io.decentury.mutliplatform.weatherkmm.viewModel

import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.decentury.mutliplatform.weatherkmm.domain.GeocodingInteractor
import io.decentury.mutliplatform.weatherkmm.domain.WeatherInteractor
import io.decentury.mutliplatform.weatherkmm.model.LoadableState
import io.decentury.mutliplatform.weatherkmm.model.Location
import io.decentury.mutliplatform.weatherkmm.utils.toFirstCapital
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val geocodingInteractor: GeocodingInteractor,
) : ViewModel() {

    private val _state: CMutableStateFlow<WeatherState> = MutableStateFlow(
        WeatherState(
            LoadableState.Initial,
            LoadableState.Initial,
            LoadableState.Initial,
        ),
    ).cMutableStateFlow()
    val state = _state.asStateFlow().cStateFlow()

    fun loadInitialData(latitude: Double, longitude: Double, locale: String) {
        Napier.d(
            "Load initial data for location. Latitude: $latitude; longitude: $longitude. " +
                "User device locale: $locale.",
        )
        viewModelScope.launch {
            val loadCurrentJobs = listOf(
                viewModelScope.launch {
                    loadCurrentLocation(latitude, longitude, locale)
                },
                viewModelScope.launch {
                    loadCurrentWeather(latitude, longitude)
                },
            )
            loadCurrentJobs.joinAll()
            loadFutureWeather(latitude, longitude)
        }
    }

    private suspend fun loadCurrentLocation(latitude: Double, longitude: Double, locale: String) {
        try {
            _state.update {
                it.copy(locationWithDate = LoadableState.Loading)
            }
            val countryWithCity = geocodingInteractor.getCountryWithCity(
                Location(latitude, longitude),
                locale,
            )
            _state.update {
                it.copy(
                    locationWithDate = LoadableState.Success(
                        WeatherState.LocationWithDate(
                            country = countryWithCity?.country,
                            city = countryWithCity?.city,
                            formattedDate = Clock.System.now()
                                .toLocalDateTime(TimeZone.currentSystemDefault())
                                .date
                                .let { date ->
                                    // Format to "Tue, Jun 30"
                                    "${date.dayOfWeek.name.substring(0, 3).toFirstCapital()}, " +
                                        "${date.month.name.substring(0, 3).toFirstCapital()} " +
                                        "${date.dayOfMonth}"
                                },
                        ),
                    ),
                )
            }
        } catch (e: Exception) {
            _state.update { it.copy(locationWithDate = LoadableState.Error(e)) }
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
                            TimeZone.currentSystemDefault().id,
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
                            TimeZone.currentSystemDefault().id,
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
