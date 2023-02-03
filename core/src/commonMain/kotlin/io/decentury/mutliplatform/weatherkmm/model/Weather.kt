package io.decentury.mutliplatform.weatherkmm.model

data class Weather(
    val type: Type,
    val temperature: Int,
    val rainFall: Int,
    val windSpeed: Int,
    val humidity: Int
) {
    enum class Type {
        CLEAR,
        PARTY_CLOUDY,
        CLOUDY,
        PARTY_CLOUDY_RAIN,
        CLOUDY_RAIN
    }
}
