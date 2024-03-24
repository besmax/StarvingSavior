package bes.max.starvingsavior.restaurants.data.repositories

import bes.max.starvingsavior.core.data.network.KtorNetworkClient
import bes.max.starvingsavior.core.data.network.NetworkClient
import bes.max.starvingsavior.core.util.ErrorType
import bes.max.starvingsavior.core.util.Resource
import bes.max.starvingsavior.restaurants.data.dto.GetRestaurantsRequest
import bes.max.starvingsavior.restaurants.data.dto.RestaurantsResponse
import bes.max.starvingsavior.restaurants.data.mappers.mapToList
import bes.max.starvingsavior.restaurants.domain.model.RestaurantModel
import bes.max.starvingsavior.restaurants.domain.repositories.RestaurantRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RestaurantRepositoryImpl(
    private val networkClient: NetworkClient
) : RestaurantRepository {
    override fun getAll(dispatcher: CoroutineDispatcher): Flow<Resource<List<RestaurantModel>>> =
        flow {
            val response = networkClient.doRequest(GetRestaurantsRequest())
            when (response.resultCode) {
                KtorNetworkClient.CODE_NO_INTERNET -> emit(Resource.Error(ErrorType.NO_INTERNET))
                KtorNetworkClient.CODE_SERVER_ERROR -> emit(Resource.Error(ErrorType.SERVER_ERROR))
                KtorNetworkClient.CODE_SUCCESS -> emit(
                    Resource.Success(
                        data = (response as RestaurantsResponse).mapToList()
                    )
                )
            }
        }.flowOn(dispatcher)
}