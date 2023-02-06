package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import org.kodein.di.compose.rememberInstance

private const val GEOLOCATION_UPDATE_INTERVAL = 1_000L
private const val SINGLE_LAUNCH_EFFECT_KEY = "SINGLE_LAUNCH_EFFECT_KEY"

@SuppressLint("MissingPermission")
@Composable
fun WeatherScreen() {
    // State
    val viewModel by rememberInstance<WeatherViewModel>()
    val state by viewModel.state.collectAsState()
    // DI
    val appContext by rememberInstance<Context>()
    // Location
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(appContext)
    }
    val locationListener = remember {
        object : LocationListener {
            override fun onLocationChanged(location: Location) {
                viewModel.loadInitialData(location.latitude, location.longitude)
                locationClient.removeLocationUpdates(this)
            }
        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (!it) return@rememberLauncherForActivityResult
            locationClient.requestLocationUpdates(
                LocationRequest.Builder(GEOLOCATION_UPDATE_INTERVAL).build(),
                locationListener,
                Looper.getMainLooper(),
            )
        },
    )
    LaunchedEffect(SINGLE_LAUNCH_EFFECT_KEY) {
        if (!isLocationAccessPermitted(appContext)) {
            launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            locationClient.requestLocationUpdates(
                LocationRequest.Builder(GEOLOCATION_UPDATE_INTERVAL).build(),
                locationListener,
                Looper.getMainLooper(),
            )
        }
    }
    Text(
        text = "Current Weather: ${state.currentWeatherState::class.simpleName.orEmpty()}\n" +
            "Future Weather: ${state.futureWeatherState::class.simpleName.orEmpty()}",
    )
    // TODO: Render state
}

@Composable
@Preview
fun WeatherPreview() {
    WeatherScreen()
}

private fun isLocationAccessPermitted(context: Context) =
    ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    ) == PackageManager.PERMISSION_GRANTED
