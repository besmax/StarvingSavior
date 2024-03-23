package bes.max.starvingsavior.restaurants.data.dto

import bes.max.starvingsavior.core.data.dto.Response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsResponse(
    @SerialName("type") var type: String? = null,
    val features: List<Feature>,
) : Response()