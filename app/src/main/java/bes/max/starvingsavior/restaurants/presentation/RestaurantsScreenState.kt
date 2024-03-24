package bes.max.starvingsavior.restaurants.presentation

import bes.max.starvingsavior.core.util.ErrorType
import bes.max.starvingsavior.restaurants.domain.model.RestaurantModel

sealed interface RestaurantsScreenState {

    data class Content(val restaurants: List<RestaurantModel>) : RestaurantsScreenState

    data object Loading : RestaurantsScreenState

    data class Error(val errorType: ErrorType) : RestaurantsScreenState
}