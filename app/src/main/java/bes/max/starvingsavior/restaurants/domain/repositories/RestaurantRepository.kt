package bes.max.starvingsavior.restaurants.domain.repositories

import bes.max.starvingsavior.core.util.Resource
import bes.max.starvingsavior.restaurants.domain.model.RestaurantModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    fun getAll(dispatcher: CoroutineDispatcher): Flow<Resource<List<RestaurantModel>>>

}