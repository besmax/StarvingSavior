package bes.max.starvingsavior.restaurants.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    val type: String?,
    val properties: Properties?,
    val geometry: Geometry?,
)

@Serializable
data class Geometry(
    val type: String?,
    val coordinates: List<Double>?,
)

@Serializable
data class Properties(
    val name: String? = null,
    val country: String? = null,
    val brand: String? = null,
    @SerialName("brand_details")
    val brandDetails: BrandDetails? = null,
    @SerialName("country_code")
    val countryCode: String? = null,
    val region: String? = null,
    val state: String? = null,
    val city: String? = null,
    val postcode: String? = null,
    val district: String? = null,
    val suburb: String? = null,
    val street: String? = null,
    val housenumber: String? = null,
    val lon: Double,
    val lat: Double,
    val formatted: String? = null,
    @SerialName("address_line1")
    val addressLine1: String? = null,
    @SerialName("address_line2")
    val addressLine2: String? = null,
    val categories: List<String>? = null,
    val details: List<String>? = null,
    val datasource: Datasource? = null,
    val website: String? = null,
    @SerialName("opening_hours")
    val openingHours: String? = null,
    @SerialName("opening_hours_covid19")
    val openingHoursCovid19: String? = null,
    @SerialName("name_international")
    val nameInternational: NameInternational? = null,
    val operator: String? = null,
    val contact: Contact? = null,
    val facilities: Facilities? = null,
    @SerialName("payment_options")
    val paymentOptions: PaymentOptions? = null,
    val catering: Catering? = null,
    @SerialName("wiki_and_media")
    val wikiAndMedia: WikiAndMedia? = null,
    @SerialName("place_id")
    val placeID: String? = null,
    @SerialName("name_other")
    val nameOther: NameOther? = null,
)

@Serializable
data class Catering(
    val cuisine: String? = null,
    val capacity: Long? = null,
    val reservation: String? = null,
    val diet: Diet? = null,
)

@Serializable
data class Diet(
    val vegetarian: Boolean? = null,
)

@Serializable
data class Contact(
    val phone: String? = null,
    val email: String? = null,
)

@Serializable
data class Datasource(
    val sourcename: String? = null,
    val attribution: String? = null,
    val license: String? = null,
    val url: String? = null,
    val raw: Raw? = null,
)

@Serializable
data class Raw(
    val bar: String? = null,
    val name: String? = null,
    val brand: String? = null,
    @SerialName("brand_details")
    val brandDetails: String? = null,
    val email: String? = null,
    val level: Int? = null,
    val phone: String? = null,
    @SerialName("osm_id")
    val osmId: Long? = null,
    val amenity: String? = null,
    @SerialName("name:fr")
    val nameFr: String? = null,
    val website: String? = null,
    val capacity: Long? = null,
    val delivery: String? = null,
    val takeaway: String? = null,
    @SerialName("addr:city")
    val addrCity: String? = null,
    @SerialName("addr:street")
    val addrStreet: String? = null,
    @SerialName("addr:postcode")
    val addrPostcode: Long? = null,
    val reservation: String? = null,
    val cuisine: String? = null,
    @SerialName("name:en")
    val nameEn: String? = null,
    @SerialName("name:ru")
    val nameRu: String? = null,
    val smoking: String? = null,
    @SerialName("int_name")
    val intName: String? = null,
    @SerialName("operator")
    val operator: String? = null,
    @SerialName("osm_type")
    val osmType: String? = null,
    val wikidata: String? = null,
    @SerialName("check_date")
    val checkDate: String? = null,
    @SerialName("contact:vk")
    val contactVk: String? = null,
    val wheelchair: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
    @SerialName("payment:visa")
    val paymentVisa: String? = null,
    @SerialName("payment:cards")
    val paymentCards: String? = null,
    @SerialName("opening_hours")
    val openingHours: String? = null,
    @SerialName("internet_access")
    val internetAccess: String? = null,
    @SerialName("outdoor_seating")
    val outdoorSeating: String? = null,
    @SerialName("internet_access:fee")
    val internetAccessFee: String? = null,
    @SerialName("addr:housenumber")
    val addrHousenumber: Long? = null,
    @SerialName("air_conditioning")
    val airConditioning: String? = null,
    @SerialName("payment:visa_debit")
    val paymentVisaDebit: String? = null,
    @SerialName("payment:contactless")
    val paymentContactless: String? = null,
    @SerialName("payment:debit_cards")
    val paymentDebitCards: String? = null,
    @SerialName("payment:credit_cards")
    val paymentCreditCards: String? = null,
    @SerialName("payment:maestro")
    val paymentMaestro: String? = null,
    @SerialName("payment:mastercard")
    val paymentMastercard: String? = null,
    @SerialName("payment:visa_electron")
    val paymentVisaElectron: String? = null,
    @SerialName("payment:cash")
    val paymentCash: String? = null,
    @SerialName("opening_hours:covid19")
    val opneningHoursCovid: String? = null,
    @SerialName("contact:email")
    val contactEmail: String? = null,
    @SerialName("contact:phone")
    val contactPhone: String? = null,
    @SerialName("brand:wikidata")
    val brandWkidata: String? = null,
    @SerialName("indoor_seating")
    val indoorSeating: String? = null,
    @SerialName("contact:website")
    val contactWebsite: String? = null,
    @SerialName("contact:instagram")
    val contactInst: String? = null,
    val microbrewery: String? = null,
    @SerialName("alt_name")
    val altName: String? = null,
    @SerialName("check_date:opening_hours")
    val checkDateOpeningHours: String? = null,
    @SerialName("diet:vegetarian")
    val dietVegetarian: String? = null,

    )

@Serializable
data class WikiAndMedia(
    val wikidata: String? = null,
)

@Serializable
data class Facilities(
    @SerialName("internet_access")
    val internetAccess: Boolean? = null,
    val wheelchair: Boolean? = null,
    val smoking: Boolean? = null,
    @SerialName("air_conditioning")
    val airConditioning: Boolean? = null,
    @SerialName("outdoor_seating")
    val outdoorSeating: Boolean? = null,
    val takeaway: Boolean? = null,
    val delivery: Boolean? = null,
    @SerialName("wheelchair_details")
    val wheelchairDetails: WheelchairDetails? = null,

    )

@Serializable
data class WheelchairDetails(
    val condition: String? = null,
)

@Serializable
data class NameInternational(
    val en: String? = null,
    val ru: String? = null,
    val fr: String? = null,
)

@Serializable
data class PaymentOptions(
    val visa: Boolean? = null,
    val maestro: Boolean? = null,
    val mastercard: Boolean? = null,
    @SerialName("visa_electron")
    val visaElectron: Boolean? = null,
    val cash: Boolean? = null,
    val cards: Boolean? = null,
    @SerialName("visa_debit")
    val visaDebit: Boolean? = null,
    val contactless: Boolean? = null,
    @SerialName("debit_cards")
    val paymentDebitCards: Boolean? = null,
    @SerialName("credit_cards")
    val paymentCreditCards: Boolean? = null,
)

@Serializable
data class BrandDetails(
    val wikidata: String? = null,
)

@Serializable
data class NameOther(
    @SerialName("alt_name")
    val altName: String? = null,
)
