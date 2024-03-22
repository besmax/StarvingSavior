package bes.max.starvingsavior.app

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import com.yandex.maps.mobile.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}