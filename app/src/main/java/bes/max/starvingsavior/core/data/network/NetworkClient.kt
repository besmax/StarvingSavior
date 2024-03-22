package bes.max.starvingsavior.core.data.network

import bes.max.starvingsavior.core.data.dto.Response

interface NetworkClient {

    suspend fun doRequest(request: Any): Response

}