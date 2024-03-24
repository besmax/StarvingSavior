package bes.max.starvingsavior.core.data.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import bes.max.starvingsavior.R
import bes.max.starvingsavior.core.domain.navigation.ExternalNavigator
import dagger.hilt.android.qualifiers.ApplicationContext

class ExternalNavigatorImpl(
    @ApplicationContext private val appContext: Context
) : ExternalNavigator {
    override fun openUrlLink(link: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(link)
        ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        startActivity(appContext, intent, null)
    }

    override fun makePhoneCall(number: String) {
        val phoneCallIntent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse(appContext.getString(R.string.tel, number))
        }
        val chooser = Intent.createChooser(phoneCallIntent, null).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(appContext, chooser, null)
    }
}