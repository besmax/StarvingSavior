package bes.max.starvingsavior.restaurants.data.dto

data class GetRestaurantsRequest(
    val filter: String = "place:51b747b70667543e4059069150501ef74d40f00101f901eb47120000000000c00206920311d0bed0bad180d183d0b320e28496203738",
    val category: String = "catering.restaurant",
    val limit: Int = 10,
)
