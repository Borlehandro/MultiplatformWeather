package io.decentury.mutliplatform.weatherkmm.network.geocoding

import io.decentury.mutliplatform.weatherkmm.model.CountryWithCity
import io.decentury.mutliplatform.weatherkmm.network.geocoding.response.GeocoderComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.path
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class GeocodingApi(private val client: HttpClient) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    suspend fun countryWithCity(
        latitude: Double,
        longitude: Double,
        locale: String,
    ): CountryWithCity? {
        client.get() {
            url(host = "geocode-maps.yandex.ru") {
                path("1.x/")
                parameter("format", "json")
                parameter("apikey", "95e4c156-f591-4011-9182-6d91d63808f5")
                parameter("geocode", "$longitude,$latitude")
                parameter("kind", "locality")
                parameter("lang", locale)
                parameter("results", 1)
            }
        }.body<String>().also {
            val components = Json.parseToJsonElement(it)
                .jsonObject["response"]
                ?.jsonObject?.get("GeoObjectCollection")
                ?.jsonObject?.get("featureMember")
                ?.jsonArray?.get(0)
                ?.jsonObject?.get("GeoObject")
                ?.jsonObject?.get("metaDataProperty")
                ?.jsonObject?.get("GeocoderMetaData")
                ?.jsonObject?.get("Address")
                ?.jsonObject?.get("Components")
                ?.let { element ->
                    json.decodeFromJsonElement<List<GeocoderComponent>>(element)
                }
            return components?.let {
                CountryWithCity(
                    country = components.first().name,
                    city = components.last().name.takeIf { components.size > 1 },
                )
            }
        }
    }
}
