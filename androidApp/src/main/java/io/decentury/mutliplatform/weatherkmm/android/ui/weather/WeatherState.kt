package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import io.decentury.mutliplatform.weatherkmm.android.common.model.LoadableState
import io.decentury.mutliplatform.weatherkmm.model.Weather

data class WeatherState(
    val locationWithDate: LoadableState<LocationWithDate>,
    val currentWeatherState: LoadableState<Weather>,
    val futureWeatherState: LoadableState<List<FutureWeatherItem>>,
) {

    data class LocationWithDate(
        val country: String?,
        val city: String?,
        val formattedDate: String,
    )

    data class FutureWeatherItem(
        val time: String,
        val type: Weather.Type,
        val temperature: Int,
    )
}
