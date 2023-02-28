package io.decentury.mutliplatform.weatherkmm.viewModel

import io.decentury.mutliplatform.weatherkmm.model.LoadableState
import io.decentury.mutliplatform.weatherkmm.model.Weather

data class WeatherState(
    val locationWithDate: LoadableState<LocationWithDate>,
    val currentWeatherState: LoadableState<Weather>,
    val futureWeatherState: LoadableState<FutureWeatherState>,
) {

    data class LocationWithDate(
        val country: String?,
        val city: String?,
        val formattedDate: String,
    )

    // Require wrap list in the data class for normal interop.
    // Issue: https://github.com/icerockdev/moko-kswift/issues/74
    data class FutureWeatherState(
        val items: List<FutureWeatherItem>,
    )

    data class FutureWeatherItem(
        val time: String,
        val type: Weather.Type,
        val temperature: Int,
    )
}
