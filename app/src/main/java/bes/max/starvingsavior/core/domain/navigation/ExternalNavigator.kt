package bes.max.starvingsavior.core.domain.navigation

interface ExternalNavigator {

    fun openUrlLink(link: String)

    fun makePhoneCall(number: String)
}