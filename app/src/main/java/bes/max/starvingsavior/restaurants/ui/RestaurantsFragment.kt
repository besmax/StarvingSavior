package bes.max.starvingsavior.restaurants.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import bes.max.starvingsavior.R
import bes.max.starvingsavior.core.util.BindingFragment
import bes.max.starvingsavior.core.util.ErrorType
import bes.max.starvingsavior.databinding.FragmentRestaurantsBinding
import bes.max.starvingsavior.restaurants.domain.model.RestaurantModel
import bes.max.starvingsavior.restaurants.presentation.RestaurantsScreenState
import bes.max.starvingsavior.restaurants.presentation.RestaurantsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantsFragment : BindingFragment<FragmentRestaurantsBinding>() {

    private val viewModel: RestaurantsViewModel by viewModels()

    private var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>? = null

    // MapKit хранит слабые ссылки на передаваемые ему Listener-объекты
    private val placemarkTapListeners = ArrayList<MapObjectTapListener>()

    private val fillBottomSheet: (RestaurantModel) -> Unit = { model ->
        binding.bottomSheetName.text = model.name
        binding.bottomSheetAddress.text =
            getString(R.string.restaurant_address, model.street, model.housenumber)
        binding.bottomSheetHours.text = model.openingHours
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRestaurantsBinding = FragmentRestaurantsBinding.inflate(inflater, container, false)

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.map.onStart()
    }

    override fun onStop() {
        binding.map.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MapKitFactory.initialize(requireContext().applicationContext)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior?.addBottomSheetCallback(getCallbackForBottomSheetState())

        binding.overlay.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect() { state -> observeViewModelState(state) }
        }
    }

    private fun observeViewModelState(state: RestaurantsScreenState) {
        when (state) {
            is RestaurantsScreenState.Loading -> showLoading()
            is RestaurantsScreenState.Content -> showContent(state)
            is RestaurantsScreenState.Error -> showError(state)
        }
    }

    private fun showContent(state: RestaurantsScreenState.Content) {
        binding.mapProgressBar.visibility = View.GONE
        binding.map.visibility = View.VISIBLE
        binding.title.visibility = View.VISIBLE
        binding.errorMessage.visibility = View.GONE

        moveMapToPosition(Point(state.restaurants[0].lat, state.restaurants[0].lon))

        val imageProvider = ImageProvider.fromResource(requireContext(), R.drawable.ic_loc_pin)
        state.restaurants.forEach { model ->
            val placemark = binding.map.mapWindow.map.mapObjects.addPlacemark().apply {
                geometry = Point(model.lat, model.lon)
                setIcon(imageProvider)
            }
            placemark.addTapListener(getPlacemarkTapListener(model))
        }
    }

    private fun showLoading() {
        binding.mapProgressBar.visibility = View.VISIBLE
        binding.map.visibility = View.GONE
        binding.title.visibility = View.GONE
        binding.errorMessage.visibility = View.GONE
    }

    private fun showError(state: RestaurantsScreenState.Error) {
        binding.mapProgressBar.visibility = View.GONE
        binding.map.visibility = View.GONE
        binding.title.visibility = View.GONE
        binding.errorMessage.visibility = View.VISIBLE
        binding.errorMessage.text = getString(
            when (state.errorType) {
                ErrorType.SERVER_ERROR -> R.string.error_server
                ErrorType.NO_INTERNET -> R.string.error_no_internet
                ErrorType.NO_CONTENT -> R.string.error_no_content
            }
        )
    }

    private fun getCallbackForBottomSheetState() =
        object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {

                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED, BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        binding.overlay.visibility = View.VISIBLE
                    }

                    else -> {
                        binding.overlay.visibility = View.GONE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        }

    private fun getPlacemarkTapListener(model: RestaurantModel): MapObjectTapListener {
        val listener = MapObjectTapListener { _, point ->
            fillBottomSheet(model)
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
            true
        }
        placemarkTapListeners.add(listener)
        return listener
    }

    private fun moveMapToPosition(point: Point) {
        binding.map.mapWindow.map.move(
            CameraPosition(
                point,
                14.5f,
                150.0f,
                30.0f
            )
        )
    }
}