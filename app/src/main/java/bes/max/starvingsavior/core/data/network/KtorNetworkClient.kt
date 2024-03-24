package bes.max.starvingsavior.core.data.network

import android.content.Context
import bes.max.starvingsavior.core.data.dto.Response
import bes.max.starvingsavior.restaurants.data.dto.GetRestaurantsRequest
import bes.max.starvingsavior.restaurants.data.dto.RestaurantsResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class KtorNetworkClient(
    private val client: HttpClient,
    private val context: Context
): NetworkClient {
    override suspend fun doRequest(request: Any): Response {
        if (!ConnectionChecker.isConnected(context)) {
            return Response().apply { resultCode = CODE_NO_INTERNET }
        }

        return when (request) {
            is GetRestaurantsRequest -> getRestaurants(request)
            else -> Response().apply { resultCode = CODE_WRONG_REQUEST }
        }
    }

    private suspend fun getRestaurants(request: GetRestaurantsRequest): Response {
        return try {
            client.get<RestaurantsResponse> {
                url(RESTAURANT_URL)
                parameter("categories", request.category)
                parameter("filter", request.filter)
                parameter("limit", request.limit)
                // normally API key is hidden in local.properties and getting from bes.max.starvingsavior.BuildConfig.GEOAPIFY_API_KEY
                parameter("apiKey", GEOAPIFY_API_KEY)
            }.apply { resultCode = CODE_SUCCESS }
        } catch (e: ClientRequestException) {
            Response().apply { resultCode = CODE_WRONG_REQUEST }
        } catch (e: ServerResponseException) {
            Response().apply { resultCode = CODE_SERVER_ERROR }
        } catch (e: Exception) {
            Response().apply { resultCode = CODE_SERVER_ERROR }
        }
    }

    companion object {
        private const val GEOAPIFY_API_KEY = "21a51f7b886348b2851ed0a246dbf089"
        private const val BASE_URL = "https://api.geoapify.com/v2"
        private const val RESTAURANT_URL = "$BASE_URL/places/"
        const val CODE_NO_INTERNET = -1
        const val CODE_SUCCESS = 200
        const val CODE_WRONG_REQUEST = 400
        const val CODE_SERVER_ERROR = 500
    }
}