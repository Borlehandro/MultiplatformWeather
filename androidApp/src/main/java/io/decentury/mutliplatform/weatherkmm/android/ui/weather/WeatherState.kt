package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import io.decentury.mutliplatform.weatherkmm.android.common.LoadableState
import io.decentury.mutliplatform.weatherkmm.model.Weather

data class WeatherState(
    val currentWeatherState: LoadableState<Weather>,
    val futureWeatherState: LoadableState<List<FutureWeatherItem>>
) {

    data class FutureWeatherItem(
        val time: String,
        val type: Weather.Type,
        val temperature: Int
    )
}
