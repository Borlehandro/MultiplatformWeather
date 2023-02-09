package io.decentury.mutliplatform.weatherkmm.android.ui.weather

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.decentury.mutliplatform.weatherkmm.android.R
import io.decentury.mutliplatform.weatherkmm.android.ui.common.Colors
import io.decentury.mutliplatform.weatherkmm.model.Weather
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import io.decentury.mutliplatform.weatherkmm.android.common.model.forceData
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
    val scrollState = rememberScrollState()
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Colors.bgWeatherScreenGradientStart,
                        Colors.bgWeatherScreenGradientCenter,
                        Colors.bgWeatherScreenGradientEnd
                    )
                )
            )
            .verticalScroll(state = scrollState)
            .padding(bottom = 16.dp)
    ) {
        TopBar()
        GeoAndDate("Sweden", "Stockholm", "Tue, Jun 30")
        state.currentWeatherState.forceData()?.let {
            TypeAndTemperature(it.temperature, it.type)
            Details(it.rainFall, it.windSpeed, it.humidity)
        }
        state.futureWeatherState.forceData()?.let { WeatherByHourList(it) }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null
        )
    }
}

@Composable
private fun GeoAndDate(
    country: String,
    city: String,
    date: String
) {
    Column(
        modifier = Modifier
            .padding(start = 24.dp)
    ) {
        Text(
            text = "$city,",
            color = Colors.mainText,
            fontSize = 40.sp
        )
        Text(
            text = country,
            color = Colors.mainText,
            fontSize = 40.sp
        )
        Text(
            text = date,
            Modifier.padding(top = 12.dp),
            color = Colors.grayText,
            fontSize = 24.sp
        )
    }
}

@Composable
private fun TypeAndTemperature(temperature: Int, type: Weather.Type) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = when (type) {
                    Weather.Type.CLEAR -> R.drawable.ic_clear
                    Weather.Type.CLOUDY -> R.drawable.ic_cloudy
                    Weather.Type.PARTY_CLOUDY -> R.drawable.ic_party_cloudy
                    Weather.Type.CLOUDY_RAIN -> R.drawable.ic_cloudy_rain
                    Weather.Type.PARTY_CLOUDY_RAIN -> R.drawable.ic_party_cloudy_rain
                }
            ),
            contentDescription = null
        )
        Column {
            Text(
                text = temperature.toString(),
                color = Colors.mainText,
                fontWeight = FontWeight.W900,
                fontSize = 90.sp
            )
            Text(
                text = stringResource(
                    id = when (type) {
                        Weather.Type.CLEAR -> R.string.weather_type_clear_text
                        Weather.Type.CLOUDY -> R.string.weather_type_cloudy_text
                        Weather.Type.PARTY_CLOUDY -> R.string.weather_type_party_cloudy_text
                        Weather.Type.CLOUDY_RAIN -> R.string.weather_type_cloudy_rain_text
                        Weather.Type.PARTY_CLOUDY_RAIN -> R.string.weather_type_party_cloudy_rain_text
                    }
                ),
                color = Colors.mainText,
                fontSize = 30.sp,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_temperature_degree),
            contentDescription = null
        )
    }
}

@Composable
private fun Details(rainfall: Int, wind: Int, humidity: Int) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        DetailsItem(
            value = rainfall,
            type = stringResource(id = R.string.info_type_rainfall),
            measure = stringResource(id = R.string.measure_cm),
            drawableId = R.drawable.ic_rainfall
        )
        DetailsItem(
            value = wind,
            type = stringResource(id = R.string.info_type_wind),
            measure = stringResource(id = R.string.measure_km_h),
            drawableId = R.drawable.ic_wind
        )
        DetailsItem(
            value = humidity,
            type = stringResource(id = R.string.info_type_humidity),
            measure = stringResource(id = R.string.measure_percent),
            drawableId = R.drawable.ic_humidity
        )
    }
}

@Composable
private fun DetailsItem(value: Int, type: String, measure: String, drawableId: Int) {
    Row(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Colors.bgGray36),
        Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(CenterVertically)
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(start = 16.dp),
                text = type,
                color = Colors.mainText
            )
        }
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(end = 30.dp),
            text = value.toString() + measure,
            color = Colors.mainText
        )
    }
}

@Composable
private fun WeatherByHourList(items: List<WeatherState.FutureWeatherItem>) {
    Column {
        Divider(
            Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            thickness = 1.dp,
            color = Colors.bgGray36
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(items) {
                WeatherByHourItem(it.time, it.temperature, it.type)
            }
        }
    }
}

@Composable
private fun WeatherByHourItem(time: String, temperature: Int, type: Weather.Type) {
    Column(
        modifier = Modifier
            .height(90.dp)
            .width(45.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Colors.bgGray36),
        Arrangement.Center
    ) {
        Text(
            text = time,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 4.dp),
            fontSize = 11.sp,
            color = Colors.grayText
        )
        Image(
            painter = painterResource(
                id = when (type) {
                    Weather.Type.CLEAR -> R.drawable.ic_clear
                    Weather.Type.CLOUDY -> R.drawable.ic_cloudy
                    Weather.Type.PARTY_CLOUDY -> R.drawable.ic_party_cloudy
                    Weather.Type.CLOUDY_RAIN -> R.drawable.ic_cloudy_rain
                    Weather.Type.PARTY_CLOUDY_RAIN -> R.drawable.ic_party_cloudy_rain
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .align(CenterHorizontally)
                .width(20.dp)
                .height(20.dp)
        )
        Text(
            text = stringResource(
                id = R.string.temperature_degrees_template,
                temperature,
            ),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 4.dp),
            fontSize = 14.sp,
            color = Colors.mainText,
            fontWeight = FontWeight.W700
        )
    }
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
