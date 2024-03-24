package bes.max.starvingsavior.restaurants.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.starvingsavior.core.util.ErrorType
import bes.max.starvingsavior.core.util.Resource
import bes.max.starvingsavior.restaurants.domain.repositories.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RestaurantsScreenState>(RestaurantsScreenState.Loading)
    val uiState: StateFlow<RestaurantsScreenState> = _uiState

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        _uiState.value = RestaurantsScreenState.Loading
        viewModelScope.launch {
            repository.getAll(dispatcher = Dispatchers.IO).collect() { response ->
                when (response) {
                    is Resource.Success<*> -> {
                        if (!response.data.isNullOrEmpty()) {
                            _uiState.value = RestaurantsScreenState.Content(
                                response.data
                            )
                            Log.d("RestaurantsViewModel", "size of list= ${response.data.size}")
                        } else {
                            _uiState.value = RestaurantsScreenState.Error(ErrorType.NO_CONTENT)
                        }
                    }

                    is Resource.Error -> {
                        _uiState.value =
                            RestaurantsScreenState.Error(
                                response.errorType ?: ErrorType.SERVER_ERROR
                            )
                    }
                }
            }
        }
    }
}