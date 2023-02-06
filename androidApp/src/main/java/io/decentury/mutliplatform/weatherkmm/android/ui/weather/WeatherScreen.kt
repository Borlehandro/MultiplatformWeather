package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.rememberInstance

@Composable
fun WeatherScreen() {
    val viewModel by rememberInstance<WeatherViewModel>()
    val state by viewModel.state.collectAsState()
    Text(
        text = "Current Weather: ${state.currentWeatherState::class.simpleName.orEmpty()}\n" +
            "Future Weather: ${state.futureWeatherState::class.simpleName.orEmpty()}"
    )
    // TODO: Render state
}

@Composable
@Preview
fun WeatherPreview() {
    WeatherScreen()
}
