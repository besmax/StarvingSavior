package bes.max.starvingsavior.app

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // normally API key is hidden in local.properties and getting from bes.max.starvingsavior.BuildConfig.MAPKIT_API_KEY
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }

    companion object {
        private const val MAPKIT_API_KEY = "3bba5f6e-41e2-442b-8ea5-0bdba9d88365"
    }
}