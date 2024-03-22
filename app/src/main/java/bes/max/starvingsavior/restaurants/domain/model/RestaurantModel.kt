package bes.max.starvingsavior.restaurants.domain.model

data class RestaurantModel(
    val name: String,
    val lon: Double,
    val lat: Double,
    val openingHours: String,
    val phone: String,
    val street: String,
    val housenumber: String,
)