package bes.max.starvingsavior.restaurants.data.dto

import bes.max.starvingsavior.core.data.dto.Response
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsResponse(
    val results: List<RestaurantDto>
) : Response()