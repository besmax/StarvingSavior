package bes.max.starvingsavior.restaurants.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(
    @SerialName("type") var type: String? = null,
    val features: List<Feature>,
)

@Serializable
data class Feature (
    val type: String,
    val properties: Properties,
    val geometry: Geometry,
)
@Serializable
data class Geometry (
    val type: String,
    val coordinates: List<Double>,
)

@Serializable
data class Properties (
    val name: String,
    val country: String,
    @SerialName("country_code")
    val countryCode: String,
    val region: String,
    val state: String,
    val city: String,
    val postcode: String,
    val district: String,
    val suburb: String,
    val street: String,
    val housenumber: String,
    val lon: Double,
    val lat: Double,
    val formatted: String,
    @SerialName("address_line1")
    val addressLine1: String,
    @SerialName("address_line2")
    val addressLine2: String,
    val categories: List<String>,
    val details: List<String>,
    val datasource: Datasource,
    val website: String,
    @SerialName("opening_hours")
    val openingHours: String,
    val contact: Contact,
    @SerialName("place_id")
    val placeID: String,
)

@Serializable
data class Contact (
    val phone: String,
)

@Serializable
data class Datasource (
    val sourcename: String,
    val attribution: String,
    val license: String,
    val url: String,
)
