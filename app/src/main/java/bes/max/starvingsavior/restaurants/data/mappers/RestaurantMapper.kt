package bes.max.starvingsavior.restaurants.data.mappers

import bes.max.starvingsavior.restaurants.data.dto.Feature
import bes.max.starvingsavior.restaurants.data.dto.RestaurantsResponse
import bes.max.starvingsavior.restaurants.domain.model.RestaurantModel

fun RestaurantsResponse.mapToList(): List<RestaurantModel> =
    features.map { it.map() }

fun Feature.map(): RestaurantModel = RestaurantModel(
    name = properties?.name,
    lon = properties?.lon ?: 0.0,
    lat = properties?.lat ?: 0.0,
    openingHours = properties?.openingHours,
    phone = properties?.contact?.phone,
    street = properties?.street,
    housenumber = properties?.housenumber,
)